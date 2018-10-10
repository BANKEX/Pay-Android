package com.bankex.pay.di.walletinfo;

import com.bankex.pay.presentation.ui.walletinfo.WalletInfoActivity;

import dagger.Subcomponent;

/**
 * Сабкомпонент экрана продробностей кошелька
 *
 * @author Denis Anisimov on 05.10.2018.
 */
@Subcomponent(modules = {WalletInfoModule.class})
@WalletInfoScope
public interface WalletInfoComponent {

    @Subcomponent.Builder
    interface Builder {
        WalletInfoComponent.Builder makeWalletInfoModule(WalletInfoModule module);

        WalletInfoComponent build();
    }

    void inject(WalletInfoActivity activity);
}
