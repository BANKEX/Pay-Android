package com.bankex.pay.di.walletinfo;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Инжектор компонента {@link WalletInfoComponent}
 *
 * @author Denis Anisimov on 05.10.2018.
 */
public class WalletInfoInjector {

    private static WalletInfoComponent sWalletInfoComponent;

    public static WalletInfoComponent getWalletInfoComponent() {
        if (sWalletInfoComponent == null) {
            sWalletInfoComponent = MainScreenInjector.getMainScreenComponent()
                    .plusWalletInfoComponent()
                    .makeWalletInfoModule(new WalletInfoModule())
                    .build();
        }
        return sWalletInfoComponent;
    }

    public static void clearWalletInfoComponent() {
        sWalletInfoComponent = null;
    }
}
