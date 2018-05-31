package com.elegion.android.template.data.provider

import com.elegion.android.template.BuildConfig
import com.elegion.android.template.util.GsonUtils

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceProvider {

    @JvmStatic
    private val retrofit: Retrofit by lazy {
        getRetrofitInstance()
    }

    @JvmStatic
    fun <T> getServiceInstance(clazz: Class<T>): T = retrofit.create(clazz)

    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(OkHttpProvider.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonUtils.requestGson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}
