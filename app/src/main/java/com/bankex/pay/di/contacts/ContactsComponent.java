package com.bankex.pay.di.contacts;

import com.bankex.pay.presentation.ui.view.contacts.ContactsFragment;

import dagger.Subcomponent;

/**
 * Сабкомпоент экрана контактов
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
@Subcomponent(modules = {ContactsModule.class})
@ContactsScope
public interface ContactsComponent {

    @Subcomponent.Builder
    interface Builder {
        ContactsComponent.Builder makeContactsModule(ContactsModule module);

        ContactsComponent build();
    }

    void inject(ContactsFragment fragment);
}
