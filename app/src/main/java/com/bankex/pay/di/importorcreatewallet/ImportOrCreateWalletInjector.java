package com.bankex.pay.di.importorcreatewallet;

import com.bankex.pay.di.user.UserComponentInjector;

/**
 * Injector for {@link ImportOrCreateWalletComponent}.
 */
public class ImportOrCreateWalletInjector {
    private static ImportOrCreateWalletComponent sImportOrCreateComponent;

    public static ImportOrCreateWalletComponent getImportOrCreateComponent() {
        if (sImportOrCreateComponent == null) {
            sImportOrCreateComponent = UserComponentInjector.getUserComponent()
                    .plusImportOrCreateComponentBuilder()
                    .makeImportOrCreateModule(new ImportOrCreateWalletModule())
                    .build();
        }
        return sImportOrCreateComponent;
    }

    public static void clearImportOrCreateComponent() {
        sImportOrCreateComponent = null;
    }
}
