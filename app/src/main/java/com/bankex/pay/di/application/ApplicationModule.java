package com.bankex.pay.di.application;

import android.content.Context;

import com.bankex.pay.BankexPayApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Основоной модуль Application
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Context provideContext() {
        return BankexPayApplication.getInstance().getApplicationContext();
    }
}
