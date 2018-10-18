package com.bankex.pay.di.module;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module for dependencies to work with server
 *
 * @author Gevork Safaryan on 11.09.2018
 */
@Module
public class NetworkModule {
	public static final String BASE_URL = "https://pay.bankex.com/";
	public static final String BASE_API = "api/";

	@Provides
	@Singleton
	Retrofit provideRetrofit() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
				.readTimeout(30, TimeUnit.SECONDS)
				.connectTimeout(30, TimeUnit.SECONDS)
				.addInterceptor(interceptor).build();
		return new Retrofit.Builder()
				.client(client)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl(BASE_URL + BASE_API)
				.build();
	}
}
