package com.bankex.pay.di.module;

import com.bankex.pay.data.network.BankexRestApi;
import com.bankex.pay.data.network.CryptoCompareRestApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Модуль предоставления зависимостей для работы с сервером
 *
 * @author Gevork Safaryan on 11.09.2018
 */
@Module
public class NetworkModule {

    //public static final String BASE_URL = "https://pay.bankex.com/";
    public static final String BASE_SCAN_URL = "https://scan.bankex.com/";
    public static final String BASE_API = "api/";
    private static final String CRYPTOCOMPARE_URL = "https://min-api.cryptocompare.com";
    public static final String SCAN = "SCAN";
    public static final String CRYPTOCOMPARE = "CRYPTOCOMPARE";


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    @Named(SCAN)
    Retrofit provideRetrofitScan(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_SCAN_URL + BASE_API)
                .build();
    }

    @Provides
    @Singleton
    @Named(CRYPTOCOMPARE)
    Retrofit provideRetrofitCrypt(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(CRYPTOCOMPARE_URL + BASE_API)
                .build();
    }

    @Provides
    @Singleton
    BankexRestApi provideBankexApi(@Named(SCAN) Retrofit retrofit) {
        return retrofit.create(BankexRestApi.class);
    }

    @Provides
    @Singleton
    CryptoCompareRestApi provideCryptoCompareApi(@Named(CRYPTOCOMPARE) Retrofit retrofit) {
        return retrofit.create(CryptoCompareRestApi.class);
    }
}
