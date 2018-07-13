package com.bankex.pay.data.local

import com.bankex.pay.data.model.ServiceException
import com.bankex.pay.data.model.Transaction
import com.bankex.pay.data.model.Wallet
import com.bankex.pay.data.provider.AccountKeystoreService
import com.bankex.pay.data.provider.BlockExplorerClient
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Numeric
import java.math.BigInteger

class TransactionDataSource(
        private val networkRepository: EthereumNetworkDataSource,
        private val accountKeystoreService: AccountKeystoreService,
        private val transactionLocalSource: TransactionInMemoryDataSource,
        private val blockExplorerClient: BlockExplorerClient) {

    fun fetchTransaction(wallet: Wallet): Array<Transaction> {
        var transactions = transactionLocalSource.fetchTransaction(wallet)
        if (transactions != null && transactions!!.size > 0) {
            return transactions
        }
        transactions = blockExplorerClient.fetchTransactions(wallet.address)!!
        transactionLocalSource.clear()
        transactionLocalSource.putTransactions(wallet, transactions)
        return transactions
    }

    fun findTransaction(wallet: Wallet, transactionHash: String): Transaction? {
        val fetchTransaction = fetchTransaction(wallet)
        for (transaction in fetchTransaction) {
            if (transaction.hash == transactionHash) {
                return transaction
            }
        }
        return null
    }

    fun createTransaction(from: Wallet, toAddress: String, subunitAmount: BigInteger, gasPrice: BigInteger, gasLimit: BigInteger, data: ByteArray, password: String): String {
        val web3j = Web3jFactory.build(HttpService(networkRepository.defaultNetwork!!.rpcServerUrl))
        val ethGetTransactionCount = web3j
                .ethGetTransactionCount(from.address, DefaultBlockParameterName.LATEST)
                .send()
        val nonce = ethGetTransactionCount.transactionCount
        val signedMessage = accountKeystoreService.signTransaction(from, password, toAddress, subunitAmount, gasPrice, gasLimit, nonce.toLong(), data, networkRepository.defaultNetwork!!.chainId.toLong())

        val raw = web3j
                .ethSendRawTransaction(Numeric.toHexString(signedMessage))
                .send()
        if (raw.hasError()) {
            throw ServiceException(raw.error.message)
        }
        return raw.transactionHash
    }

    private fun onNetworkChanged() {
        transactionLocalSource.clear()
    }
}
