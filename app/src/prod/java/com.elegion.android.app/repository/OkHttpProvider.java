package com.elegion.android.app.repository;

import android.support.annotation.NonNull;

import com.elegion.android.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Daniel Serdyukov
 */
public final class OkHttpProvider {

    private OkHttpProvider() {
        throw new IllegalStateException("Final class can not be instantiated");
    }

    @NonNull
    public static OkHttpClient provideClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return new OkHttpClient.Builder().build();
    }

}
