package com.bankex.pay.di.wallet;

import com.bankex.pay.presentation.ui.home.WalletFragment;

import dagger.Subcomponent;

/**
 * Сабкомпонент главного экрана
 *
 * @author Gevork Safaryan on 11.09.2018.
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
