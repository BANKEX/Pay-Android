package com.bankex.pay.di.wallet;

import com.bankex.pay.domain.interactor.IPayWalletInteractor;
import com.bankex.pay.presentation.presenter.WalletPresenter;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;
import dagger.Module;
import dagger.Provides;

/**
 * Module for wallet main screen.
 */
@Module
public class WalletModule {
	@WalletScope
	@Provides
	WalletPresenter provideWalletPresenter(IPayWalletInteractor payWalletInteractor, IRxSchedulersUtils rxSchedulersUtils) {
		return new WalletPresenter(payWalletInteractor, rxSchedulersUtils);
	}
}
