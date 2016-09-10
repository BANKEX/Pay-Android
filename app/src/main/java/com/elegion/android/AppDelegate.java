package com.elegion.android;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.elegion.android.app.Lifecycler;
import com.elegion.android.repository.OkHttpProvider;
import com.elegion.android.repository.RxSQLiteProvider;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rxsqlite.RxSQLite;
import sqlite4a.SQLiteDb;

/**
 * @author Daniel Serdyukov
 */
public class AppDelegate extends Application {

    private static Retrofit sRetrofit;

    private static Context sAppContext;

    static {
        SQLiteDb.loadLibrary();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
        }
        Lifecycler.register(this);
        RxSQLite.register(RxSQLiteProvider.provideClient(this));
    }

    public static Context getAppContext() {
        return sAppContext;
    }

    public static Retrofit getRetrofitInstance() {
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
