package com.bankex.pay.di.module;

import com.bankex.pay.presentation.ui.navigation.base.BankexRouter;
import com.bankex.pay.presentation.ui.navigation.base.IBankexRouter;
import com.bankex.pay.presentation.ui.navigation.home.IMainRouter;
import com.bankex.pay.presentation.ui.navigation.home.MainRouter;
import com.bankex.pay.presentation.ui.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.ui.navigation.wallet.WalletRouter;

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
    IBankexRouter provideBankexRouter() {
        return new BankexRouter();
    }

    @Provides
    @Singleton
    IMainRouter provideHomeRouter() {
        return new MainRouter();
    }

    @Provides
    @Singleton
    IWalletRouter provideWalletRouter() {
        return new WalletRouter();
    }
}
