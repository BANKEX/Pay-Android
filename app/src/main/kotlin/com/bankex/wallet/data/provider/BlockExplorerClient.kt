package com.bankex.wallet.data.provider

import com.bankex.wallet.data.local.EthereumNetworkDataSource
import com.bankex.wallet.data.local.OnNetworkChangeListener
import com.bankex.wallet.data.model.NetworkInfo
import com.bankex.wallet.data.model.Transaction
import com.google.gson.Gson

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class BlockExplorerClient(
        private val httpClient: OkHttpClient,
        private val gson: Gson,
        private val networkRepository: EthereumNetworkDataSource) {

    private var etherScanApiClient: EtherScanApiClient? = null

    init {
        val networkInfo = networkRepository.defaultNetwork
        onNetworkChanged(networkInfo!!)
    }

    private fun buildApiClient(baseUrl: String) {
        etherScanApiClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(EtherScanApiClient::class.java)
    }


    fun fetchTransactions(address: String): Array<Transaction>? {
        val etherScanResponseResponse = etherScanApiClient!!.fetchTransactions(address)
        return etherScanResponseResponse.body()!!.docs
    }

    private fun onNetworkChanged(networkInfo: NetworkInfo) {
        buildApiClient(networkInfo.backendUrl)
    }

    private interface EtherScanApiClient {
        @GET("/transactions?limit=50")
        fun fetchTransactions(
                @Query("address") address: String):
        // TODO: startBlock - it's pagination. Not work now
                Response<EtherScanResponse>
    }

    private class EtherScanResponse {
        internal var docs: Array<Transaction>? = null
    }

}
