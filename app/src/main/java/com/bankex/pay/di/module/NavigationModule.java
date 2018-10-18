package com.bankex.pay.di.module;

import com.bankex.pay.presentation.navigation.base.BankexRouter;
import com.bankex.pay.presentation.navigation.base.IBankexRouter;
import com.bankex.pay.presentation.navigation.home.HomeRouter;
import com.bankex.pay.presentation.navigation.home.IHomeRouter;
import com.bankex.pay.presentation.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.navigation.wallet.WalletRouter;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Navigation module
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
