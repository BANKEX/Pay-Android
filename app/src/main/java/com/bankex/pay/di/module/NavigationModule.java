package com.bankex.pay.di.module;

import com.bankex.pay.presentation.ui.navigation.base.BankexRouter;
import com.bankex.pay.presentation.ui.navigation.base.IBankexRouter;
import com.bankex.pay.presentation.ui.navigation.home.HomeRouter;
import com.bankex.pay.presentation.ui.navigation.home.IHomeRouter;
import com.bankex.pay.presentation.ui.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.ui.navigation.wallet.WalletRouter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Navigation
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
	IHomeRouter provideHomeRouter() {
		return new HomeRouter();
	}

	@Provides
	@Singleton
	IWalletRouter provideWalletRouter() {
		return new WalletRouter();
	}
}
