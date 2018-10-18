package com.bankex.pay.di.wallet;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Injector for {@link WalletComponent}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class WalletInjector {

    private static WalletComponent sWalletComponent;

    public static WalletComponent getWalletComponent() {
        if (sWalletComponent == null) {
            sWalletComponent = MainScreenInjector.getMainScreenComponent()
                    .plusWalletComponent()
                    .makeWalletModule(new WalletModule())
                    .build();
        }
        return sWalletComponent;
    }

    public static void clearWalletComponent() {
        sWalletComponent = null;
    }
}
