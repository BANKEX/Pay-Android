package com.bankex.pay.di.contacts.addcontact;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Инжектор для фрагмента с конткатами
 *
 * @author Denis Anisimov.
 */
public class AddContactInjector {
    private static AddContactComponent sAddContactComponent;

    public static AddContactComponent getAddContactComponent() {
        if (sAddContactComponent == null) {
            sAddContactComponent = MainScreenInjector.getMainScreenComponent()
                    .plusAddContactComponent()
                    .makeAddContactModule(new AddContactModule())
                    .build();
        }
        return sAddContactComponent;
    }

    public static void clearAddContactComponent() {
        sAddContactComponent = null;
    }
}
