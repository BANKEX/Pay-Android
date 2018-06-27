package com.elegion.android.bankex.data.local

import com.elegion.android.bankex.data.model.NetworkInfo
import com.elegion.android.bankex.data.provider.EthplorerTokenService
import okhttp3.OkHttpClient
import org.web3j.protocol.Web3j
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.http.HttpService

class TokenDataSource(
        private val httpClient: OkHttpClient,
        private val ethereumNetworkRepository: EthereumNetworkDataSource,
        private val tokenNetworkService: EthplorerTokenService,
        private val tokenLocalSource: TokenLocalSource) {
    private var web3j: Web3j? = null

    init {
        //  ethereumNetworkRepository.addOnChangeDefaultNetwork(web3j. )
        buildWeb3jClient(ethereumNetworkRepository.defaultNetwork!!)
    }

    private fun buildWeb3jClient(defaultNetwork: NetworkInfo) {
        web3j = Web3jFactory.build(HttpService(defaultNetwork.rpcServerUrl, httpClient, false))
    }

    /* fun fetch(walletAddress: String): Observable<Array<Token>> {
         return Observable.create<Array<Token>> { e ->
             val defaultNetwork = ethereumNetworkRepository.getDefaultNetwork()
             val wallet = Wallet(walletAddress)
             var tokens = tokenLocalSource.fetch(defaultNetwork, wallet)
                     .map({ items ->
                         val len = items.length
                         val result = arrayOfNulls<Token>(len)
                         for (i in 0 until len) {
                             result[i] = Token(items[i], null)
                         }
                         result
                     })
                     .blockingGet()
             e.onNext(tokens)

             updateTokenInfoCache(defaultNetwork, wallet)
             tokens = tokenLocalSource.fetch(defaultNetwork, wallet)
                     .map({ items ->
                         val len = items.length
                         val result = arrayOfNulls<Token>(len)
                         for (i in 0 until len) {
                             var balance: BigDecimal? = null
                             try {
                                 balance = getBalance(wallet, items[i])
                             } catch (e1: Exception) {
                                 Log.d("TOKEN", "Err", e1)
                                 *//* Quietly *//*
                            }

                            result[i] = Token(items[i], balance)
                        }
                        result
                    }).blockingGet()
            e.onNext(tokens)
        }
    }

    fun addToken(wallet: Wallet, address: String, symbol: String, decimals: Int): Completable {
        return tokenLocalSource.put(
                ethereumNetworkRepository.getDefaultNetwork(),
                wallet,
                TokenInfo(address, "", symbol, decimals))
    }

    private fun updateTokenInfoCache(defaultNetwork: NetworkInfo, wallet: Wallet) {
        if (!defaultNetwork.isMainNetwork) {
            return
        }
        tokenNetworkService
                .fetch(wallet.address)
                .flatMapCompletable({ items ->
                    Completable.fromAction {
                        for (tokenInfo in items) {
                            try {
                                tokenLocalSource.put(
                                        ethereumNetworkRepository.getDefaultNetwork(), wallet, tokenInfo)
                                        .blockingAwait()
                            } catch (t: Throwable) {
                                Log.d("TOKEN_REM", "Err", t)
                            }

                        }
                    }
                })
                .blockingAwait()
    }

    @Throws(Exception::class)
    private fun getBalance(wallet: Wallet, tokenInfo: TokenInfo): BigDecimal? {
        val function = balanceOf(wallet.address)
        val responseValue = callSmartContractFunction(function, tokenInfo.address, wallet)

        val response = FunctionReturnDecoder.decode(
                responseValue, function.outputParameters)
        return if (response.size == 1) {
            BigDecimal((response[0] as Uint256).value)
        } else {
            null
        }
    }

    @Throws(Exception::class)
    private fun callSmartContractFunction(
            function: org.web3j.abi.datatypes.Function, contractAddress: String, wallet: Wallet): String {
        val encodedFunction = FunctionEncoder.encode(function)

        val response = web3j!!.ethCall(
                Transaction.createEthCallTransaction(wallet.address, contractAddress, encodedFunction),
                DefaultBlockParameterName.LATEST)
                .sendAsync().get()

        return response.value
    }

    companion object {

        private fun balanceOf(owner: String): org.web3j.abi.datatypes.Function {
            return org.web3j.abi.datatypes.Function(
                    "balanceOf",
                    listOf<Type>(Address(owner)),
                    listOf<TypeReference<*>>(object : TypeReference<Uint256>() {

                    }))
        }

        fun createTokenTransferData(to: String, tokenAmount: BigInteger): ByteArray {
            val params = Arrays.asList(Address(to), Uint256(tokenAmount))

            val returnTypes = Arrays.asList<TypeReference<*>>(object : TypeReference<Bool>() {

            })

            val function = Function("transfer", params, returnTypes)
            val encodedFunction = FunctionEncoder.encode(function)
            return Numeric.hexStringToByteArray(Numeric.cleanHexPrefix(encodedFunction))
        }
    }*/
}
