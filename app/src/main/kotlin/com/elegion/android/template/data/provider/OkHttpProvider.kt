package com.elegion.android.template.data.provider

import com.elegion.android.template.BuildConfig

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpProvider {
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
