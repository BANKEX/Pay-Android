package com.bankex.wallet.data.provider

import com.bankex.wallet.data.model.TokenInfo
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class EthplorerTokenService(
        httpClient: OkHttpClient,
        gson: Gson) {

    private val ethplorerApiClient: EthplorerApiClient

    init {
        ethplorerApiClient = Retrofit.Builder()
                .baseUrl(ETHPLORER_API_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(EthplorerApiClient::class.java)
    }


    fun fetch(walletAddress: String): Array<TokenInfo?> {
        val res = ethplorerApiClient.fetchTokens(walletAddress)
        val r = res.body()
        if (r?.tokens == null) {
            return emptyArray()
        } else {
            val len = r.tokens!!.size
            val result = arrayOfNulls<TokenInfo>(len)
            for (i in 0 until len) {
                result[i] = r.tokens!![i].tokenInfo
            }
            return result
        }

    }

    interface EthplorerApiClient {
        @GET("/getAddressInfo/{address}?apiKey=freekey")
        fun fetchTokens(@Path("address") address: String): Response<EthplorerResponse>
    }

    class Token {
        internal var tokenInfo: TokenInfo? = null
    }

    class EthplorerResponse {
        internal var tokens: Array<Token>? = null
        internal var error: EthplorerError? = null
    }

    class EthplorerError {
        internal var code: Int = 0
        internal var message: String? = null
    }

    companion object {
        private val ETHPLORER_API_URL = "https://api.ethplorer.io"


    }
}
