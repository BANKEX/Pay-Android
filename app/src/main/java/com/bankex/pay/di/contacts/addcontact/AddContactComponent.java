package com.bankex.pay.di.contacts.addcontact;

import com.bankex.pay.presentation.ui.home.ContactFragment;
import com.bankex.pay.presentation.ui.home.addcontacts.AddContactFragment;

import dagger.Subcomponent;

/**
 * Временный сабкомпонент для вфрагмента с контактами
 *
 * @author Denis Anisimov.
 */
@Subcomponent(modules = AddContactModule.class)
@AddContactScope
public interface AddContactComponent {

    @Subcomponent.Builder
    interface Builder {
        AddContactComponent.Builder makeAddContactModule(AddContactModule module);

        AddContactComponent build();
    }

    void inject(AddContactFragment fragment);
}
