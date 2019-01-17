package com.bankex.pay.di.wallet;

import com.bankex.pay.presentation.ui.home.WalletFragment;

import dagger.Subcomponent;

/**
 * Sub component for wallet main screen.
 */
@Subcomponent(modules = {WalletModule.class})
@WalletScope
public interface WalletComponent {

    @Subcomponent.Builder
    interface Builder {
        WalletComponent.Builder makeWalletModule(WalletModule module);

        WalletComponent build();
    }

    void inject(WalletFragment fragment);
}
