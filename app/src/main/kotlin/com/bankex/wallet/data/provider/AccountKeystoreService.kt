package com.bankex.wallet.data.provider


import com.bankex.wallet.data.model.Wallet

import java.math.BigInteger

import io.reactivex.Single

interface AccountKeystoreService {
    /**
     * Create account in keystore
     * @param password account password
     * @return new [Wallet]
     */
    fun createAccount(password: String): Wallet

    /**
     * Include new existing keystore
     * @param store store to include
     * @param password store password
     * @return included [Wallet] if success
     */
    fun importKeystore(store: String, password: String, newPassword: String): Wallet

    fun importPrivateKey(privateKey: String, newPassword: String): Wallet

    /**
     * Export wallet to keystore
     * @param wallet wallet to export
     * @param password password from wallet
     * @param newPassword new password to store
     * @return store data
     */
    fun exportAccount(wallet: Wallet, password: String, newPassword: String): String

    /**
     * Delete account from keystore
     * @param address account address
     * @param password account password
     */
    fun deleteAccount(address: String, password: String)

    /**
     * Sign transaction
     * @param signer [Wallet]
     * @param signerPassword password from [Wallet]
     * @param toAddress transaction destination address
     * @param wei
     * @param nonce
     * @return sign data
     */
    fun signTransaction(
            signer: Wallet,
            signerPassword: String,
            toAddress: String,
            amount: BigInteger,
            gasPrice: BigInteger,
            gasLimit: BigInteger,
            nonce: Long,
            data: ByteArray,
            chainId: Long): ByteArray
    /**
     * Check if there is an address in the keystore
     * @param address [Wallet] address
     */
    fun hasAccount(address: String): Boolean

    /**
     * Return all [Wallet] from keystore
     * @return wallets
     */
    fun fetchAccounts(): Array<Wallet?>
}
