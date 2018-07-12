package com.bankex.wallet.data.provider

import com.bankex.wallet.BuildConfig

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpProvider {

    @JvmStatic
    val okHttpClient: OkHttpClient by lazy {
        provideClient()
    }

    @JvmStatic
    fun provideClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        // logs
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder.build()
    }
}
