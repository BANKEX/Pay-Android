package com.elegion.android.template.data.provider;

import android.support.annotation.NonNull;

import com.elegion.android.template.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Mikhail Barannikov
 */
public final class OkHttpProvider {
    private OkHttpProvider() {
        throw new IllegalStateException("Final class can not be instantiated");
    }

    @NonNull
    public static OkHttpClient provideClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // logs
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return builder.build();
    }

}
