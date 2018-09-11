package com.bankex.pay.di.module;

import com.bankex.pay.presentation.ui.navigation.BankexRouter;
import com.bankex.pay.presentation.ui.navigation.IBankexRouter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль навигации
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class NavigationModule {

    @Provides
    @Singleton
    IBankexRouter provideRouter() {
        return new BankexRouter();
    }
}
