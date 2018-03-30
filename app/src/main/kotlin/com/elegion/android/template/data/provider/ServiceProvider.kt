package com.elegion.android.template.data.provider

import com.elegion.android.template.BuildConfig
import com.elegion.android.template.util.GsonUtils

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceProvider {

    private lateinit var sRetrofit: Retrofit

    @JvmStatic
    fun <T> getServiceInstance(clazz: Class<T>): T = getRetrofitInstance().create(clazz)

    private fun getRetrofitInstance(): Retrofit {
        if (!::sRetrofit.isInitialized) {
            sRetrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .client(OkHttpProvider.provideClient())
                    .addConverterFactory(GsonConverterFactory.create(GsonUtils.requestGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return sRetrofit
    }
}
