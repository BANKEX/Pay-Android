package com.bankex.pay.di.mainscreen;

import com.bankex.pay.domain.interactor.IPayWalletInteractor;
import com.bankex.pay.presentation.presenter.MainScreenPresenter;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;
import dagger.Module;
import dagger.Provides;

/**
 * Main screen module.
 */
@Module
public class MainScreenModule {
	@Provides
	@MainScreenScope
	MainScreenPresenter provideMainScreenPresenter(IPayWalletInteractor payWalletInteractor, IRxSchedulersUtils rxSchedulersUtils) {
		return new MainScreenPresenter(payWalletInteractor, rxSchedulersUtils);
	}
}
