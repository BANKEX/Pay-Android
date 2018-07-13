package com.bankex.pay.data

import android.content.Context
import com.bankex.pay.data.local.EthereumNetworkDataSource
import com.bankex.pay.data.local.PreferencesDataSource
import com.bankex.pay.data.local.WalletDataSource
import com.bankex.pay.data.provider.GethKeystoreAccountProvider
import com.bankex.pay.data.provider.OkHttpProvider
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import java.io.File
import java.math.BigInteger

class Repository private constructor(val context: Context) {
    private val preferencesDataSource: PreferencesDataSource = PreferencesDataSource(context)
    private val ethereumNetworkDataSource: EthereumNetworkDataSource = EthereumNetworkDataSource(preferencesDataSource)
    private val gethKeystoreAccountProvider: GethKeystoreAccountProvider = GethKeystoreAccountProvider(File(context.filesDir, "store"))
    val walletDataSource: WalletDataSource = WalletDataSource(OkHttpProvider.provideClient(), preferencesDataSource, gethKeystoreAccountProvider,ethereumNetworkDataSource)

    private val clientId = "01d211db9c3e6dd5effd"
    private val clientSecret = "244d5f543b86f5dc6dd9555c6534cf8e86e46976"

    var onBoardingFlag: Boolean
        get() = preferencesDataSource.onBoardingFlag
        set(onBoarding) {
            preferencesDataSource.onBoardingFlag = onBoarding
        }

    suspend fun getEtheriumBalance(): BigInteger {
        val result = Web3jFactory.build(HttpService("https://ropsten.etherscan.io")).ethGetBalance("0x92278E21889fd1E72bD59048AC042561D39E8A52", DefaultBlockParameterName.EARLIEST)
        val sendAsync = result.sendAsync()
        val get = sendAsync.get()
        val balance = get.balance
        return balance
    }


    companion object {
        private const val DELAY = 1500L
        @JvmStatic
        private var instance: Repository? = null

        @JvmStatic
        @Synchronized
        fun get(context: Context): Repository {
            if (instance == null) {
                instance = Repository(context)
            }
            return instance as Repository
        }
    }
}
