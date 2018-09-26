package com.bankex.pay.di.contacts;

import android.arch.lifecycle.Lifecycle;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Инжектор для фрагмента с конткатами
 *
 * @author Denis Anisimov.
 */
public class ContactsInjector {
    private static ContactsComponent sContactsComponent;

    public static ContactsComponent getContactsComponent(Lifecycle lifecycle) {
        if (sContactsComponent == null) {
            sContactsComponent = MainScreenInjector.getMainScreenComponent()
                    .plusContactsComponent()
                    .makeContactsModule(new ContactsModule(lifecycle))
                    .build();
        }
        return sContactsComponent;
    }

    public static void clearContactsComponent() {
        sContactsComponent = null;
    }
}
