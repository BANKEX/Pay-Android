package com.elegion.android;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.elegion.android.app.Lifecycler;
import com.elegion.android.repository.OkHttpProvider;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
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
    }

    public static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_BASE_URL)
                    .client(OkHttpProvider.provideClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
