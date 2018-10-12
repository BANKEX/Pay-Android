package com.bankex.pay.di.contacts;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Инжектор экрана контактов
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
public class ContactsInjector {
    private static ContactsComponent sContactsComponent;

    public static ContactsComponent getContactsComponent() {
        if (sContactsComponent == null) {
            sContactsComponent = MainScreenInjector.getMainScreenComponent()
                    .plusContactsComponentBuilder()
                    .makeContactsModule(new ContactsModule())
                    .build();
        }
        return sContactsComponent;
    }

    public static void clearContactsComponent() {
        sContactsComponent = null;
    }
}
