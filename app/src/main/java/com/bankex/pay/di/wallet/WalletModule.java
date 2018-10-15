package com.bankex.pay.di.wallet;

import com.bankex.pay.domain.interactor.IPayWalletInteractor;
import com.bankex.pay.presentation.presenter.wallet.WalletPresenter;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Module for Wallet screen
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class WalletModule {

    @WalletScope
    @Provides
    WalletPresenter provideWalletPresenter(IPayWalletInteractor payWalletInteractor, IRxSchedulersUtils rxSchedulersUtils) {
        return new WalletPresenter(payWalletInteractor, rxSchedulersUtils);
    }

}
