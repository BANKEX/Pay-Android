package com.bankex.pay.data.provider

import com.bankex.pay.data.model.ServiceException
import com.bankex.pay.data.model.Wallet
import com.fasterxml.jackson.databind.ObjectMapper
import org.ethereum.geth.*
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Wallet.create
import java.io.File
import java.math.BigInteger
import java.nio.charset.Charset

class GethKeystoreAccountProvider : AccountKeystoreService {

    private val keyStore: KeyStore

    constructor(keyStoreFile: File) {
        keyStore = KeyStore(keyStoreFile.absolutePath, Geth.LightScryptN, Geth.LightScryptP)
    }

    constructor(keyStore: KeyStore) {
        this.keyStore = keyStore
    }

    override fun createAccount(password: String): Wallet {
        val newAccount = keyStore.newAccount(password)
        newAccount.address
        return Wallet(newAccount.address.hex.toLowerCase())
    }

    override fun importKeystore(store: String, password: String, newPassword: String): Wallet {
        val account = keyStore
                .importKey(store.toByteArray(Charset.forName("UTF-8")), password, newPassword)
        return Wallet(account.address.hex.toLowerCase())

    }

    override fun importPrivateKey(privateKey: String, newPassword: String): Wallet {
        val key = BigInteger(privateKey, PRIVATE_KEY_RADIX)
        val keypair = ECKeyPair.create(key)
        val walletFile = create(newPassword, keypair, N, P)
        val writeValueAsString = ObjectMapper().writeValueAsString(walletFile)
        return importKeystore(writeValueAsString, newPassword, newPassword)
    }

    override fun exportAccount(wallet: Wallet, password: String, newPassword: String): String {
        val findAccount = findAccount(wallet.address)
        return String(keyStore.exportKey(findAccount, password, newPassword))
    }

    override fun deleteAccount(address: String, password: String) {
        val account = findAccount(address)
        keyStore.deleteAccount(account, password)
    }

    override fun signTransaction(signer: Wallet, signerPassword: String, toAddress: String, amount: BigInteger, gasPrice: BigInteger, gasLimit: BigInteger, nonce: Long, data: ByteArray, chainId: Long): ByteArray {

        val value = BigInt(0)
        value.setString(amount.toString(), 10)

        val gasPriceBI = BigInt(0)
        gasPriceBI.setString(gasPrice.toString(), 10)

        val gasLimitBI = BigInt(0)
        gasLimitBI.setString(gasLimit.toString(), 10)

        val tx = Transaction(
                nonce,
                Address(toAddress),
                value,
                gasLimitBI,
                gasPriceBI,
                data)

        val chain = BigInt(chainId) // Chain identifier of the main net
        val gethAccount = findAccount(signer.address)
        keyStore.unlock(gethAccount, signerPassword)
        val signed = keyStore.signTx(gethAccount, tx, chain)
        keyStore.lock(gethAccount.address)

        return signed.encodeRLP()
    }

    override fun hasAccount(address: String): Boolean {
        return keyStore.hasAddress(Address(address))
    }

    override fun fetchAccounts(): Array<Wallet?> {
        val accounts = keyStore.accounts
        val len = accounts.size().toInt()
        val result = arrayOfNulls<Wallet>(len)

        for (i in 0 until len) {
            val gethAccount = accounts.get(i.toLong())
            result[i] = Wallet(gethAccount.address.hex.toLowerCase())
        }

        return result
    }

    @Throws(ServiceException::class)
    private fun findAccount(address: String): org.ethereum.geth.Account {
        val accounts = keyStore.accounts
        val len = accounts.size().toInt()
        for (i in 0 until len) {
            try {
                android.util.Log.d("ACCOUNT_FIND", "Address: " + accounts.get(i.toLong()).address.hex)
                if (accounts.get(i.toLong()).address.hex.equals(address, ignoreCase = true)) {
                    return accounts.get(i.toLong())
                }
            } catch (ex: Exception) {
                /* Quietly: interest only result, maybe next is ok. */
            }

        }
        throw ServiceException("Wallet with address: $address not found")
    }

    companion object {
        private val PRIVATE_KEY_RADIX = 16
        /**
         * CPU/Memory cost parameter. Must be larger than 1, a power of 2 and less than 2^(128 * r / 8).
         */
        private val N = 1 shl 9
        /**
         * Parallelization parameter. Must be a positive integer less than or equal to Integer.MAX_VALUE / (128 * r * 8).
         */
        private val P = 1
    }
}
