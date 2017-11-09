package com.elegion.android.template.data.provider;

import android.support.annotation.NonNull;

import com.elegion.android.template.BuildConfig;
import com.elegion.android.template.util.GsonUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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
                    .addConverterFactory(GsonConverterFactory.create(GsonUtils.requestGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
