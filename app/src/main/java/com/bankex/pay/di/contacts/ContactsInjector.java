package com.bankex.pay.di.contacts;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Инжектор для фрагмента с конткатами
 *
 * @author Denis Anisimov.
 */
public class ContactsInjector {
    private static ContactsComponent sContactsComponent;

    public static ContactsComponent getContactsComponent() {
        if (sContactsComponent == null) {
            sContactsComponent = MainScreenInjector.getMainScreenComponent()
                    .plusContactsComponent()
                    .makeContactsModule(new ContactsModule())
                    .build();
        }
        return sContactsComponent;
    }

    public static void clearContactsComponent() {
        sContactsComponent = null;
    }
}
