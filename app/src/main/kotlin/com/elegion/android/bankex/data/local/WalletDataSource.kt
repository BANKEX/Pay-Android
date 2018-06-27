package com.elegion.android.bankex.data.local

import com.elegion.android.bankex.data.model.Wallet
import com.elegion.android.bankex.data.provider.AccountKeystoreService
import kotlinx.coroutines.experimental.async
import okhttp3.OkHttpClient
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import java.math.BigInteger

class WalletDataSource(
        private val httpClient: OkHttpClient,
        private val preferenceRepositoryType: PreferencesDataSource,
        private val accountKeystoreService: AccountKeystoreService,
        private val networkRepository: EthereumNetworkDataSource) {

    val defaultWallet: Wallet?
        get() = findWallet(preferenceRepositoryType.getCurrentWalletAddress())

    fun fetchWallets(): Array<Wallet?> {
        return accountKeystoreService.fetchAccounts()
    }

    fun findWallet(address: String?): Wallet? {
        val fetchWallets = fetchWallets()
        for (wallet in fetchWallets) {
            if (wallet!!.sameAddress(address)) {
                return wallet
            }
        }
        return null
    }

    fun createWallet(password: String) = accountKeystoreService.createAccount(password)

    suspend fun importKeystoreToWallet(store: String, password: String, newPassword: String): Wallet {
        return accountKeystoreService.importKeystore(store, password, newPassword)
    }

    suspend fun importPrivateKeyToWallet(privateKey: String, newPassword: String): Wallet {
        return accountKeystoreService.importPrivateKey(privateKey, newPassword)
    }

    suspend fun exportWallet(wallet: Wallet, password: String, newPassword: String): String {
        return accountKeystoreService.exportAccount(wallet, password, newPassword)
    }

    suspend fun deleteWallet(address: String, password: String) {
        return accountKeystoreService.deleteAccount(address, password)
    }

    suspend fun setDefaultWallet(wallet: Wallet) {
        return preferenceRepositoryType.setCurrentWalletAddress(wallet.address)
    }

    suspend fun balanceInWei(wallet: Wallet): BigInteger {
        return Web3jFactory
                .build(HttpService(networkRepository.defaultNetwork!!.rpcServerUrl, httpClient, false))
                .ethGetBalance(wallet.address, DefaultBlockParameterName.LATEST)
                .send()
                .balance
    }
}