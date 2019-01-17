package com.bankex.pay.di.applicationmodules;

import com.bankex.pay.presentation.navigation.base.BankexRouter;
import com.bankex.pay.presentation.navigation.base.IBankexRouter;
import com.bankex.pay.presentation.navigation.home.IMainRouter;
import com.bankex.pay.presentation.navigation.home.MainRouter;
import com.bankex.pay.presentation.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.navigation.wallet.WalletRouter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Navigation module.
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
