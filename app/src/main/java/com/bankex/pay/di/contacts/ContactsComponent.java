package com.bankex.pay.di.contacts;

import com.bankex.pay.presentation.ui.home.ContactFragment;

import dagger.Subcomponent;

/**
 * Временный сабкомпонент для вфрагмента с контактами
 *
 * @author Denis Anisimov.
 */
@Subcomponent(modules = ContactsModule.class)
@ContactsScope
public interface ContactsComponent {

    @Subcomponent.Builder
    interface Builder {
        ContactsComponent.Builder makeContactsModule(ContactsModule module);

        ContactsComponent build();
    }

    void inject(ContactFragment fragment);
}
