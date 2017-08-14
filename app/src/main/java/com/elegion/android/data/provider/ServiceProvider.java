package com.elegion.android.data.provider;

import android.support.annotation.NonNull;

import com.elegion.android.BuildConfig;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Mikhail Barannikov
 */
public final class ServiceProvider {
    private static Retrofit sRetrofit;

    private ServiceProvider() {
        throw new IllegalStateException("Final class can not be instantiated");
    }

    @NonNull
    public static <T> T getServiceInstance(Class<T> clazz) {
        return getRetrofitInstance().create(clazz);
    }

    private static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .client(OkHttpProvider.provideClient())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
