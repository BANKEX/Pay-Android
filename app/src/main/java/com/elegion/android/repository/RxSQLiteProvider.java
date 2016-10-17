package com.elegion.android.repository;

import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.BuildConfig;

import rxsqlite.RxSQLiteClient;

/**
 * @author Daniel Serdyukov
 */
public final class RxSQLiteProvider {

    private static final int DATABASE_VERSION = 1;

    private RxSQLiteProvider() {
        //Not implemented
    }

    @NonNull
    public static RxSQLiteClient provideClient(@NonNull Context context) {
        final RxSQLiteClient.Builder builder = RxSQLiteClient.builder(context, DATABASE_VERSION);
        if (BuildConfig.DEBUG) {
            builder.enableTracing();
        }
        return builder.build();
    }

}
