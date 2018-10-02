package com.bankex.pay.di.contacts.addcontact;

import com.bankex.pay.data.reporitories.ContactRepository;
import com.bankex.pay.di.contacts.ContactsModule;
import com.bankex.pay.presentation.presenter.contacts.AddContactPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль для фрагмента с контактами
 *
 * @author Denis Anisimov.
 */
@Module(includes = ContactsModule.class)
public class AddContactModule {


    /*@Provides
    @AddContactScope
    AddContactPresenter provideAddContactPresenter(ContactRepository contactRepository) {
        return new AddContactPresenter(contactRepository);
    }*/
}
