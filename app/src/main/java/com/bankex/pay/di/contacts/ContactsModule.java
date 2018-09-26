package com.bankex.pay.di.contacts;

import com.bankex.pay.data.reporitories.ContactRepository;
import com.bankex.pay.data.reporitories.ContactsDataSourceRemote;
import com.bankex.pay.di.module.FireBaseModule;
import com.bankex.pay.presentation.presenter.contacts.ContactsPresenter;
import com.bankex.pay.presentation.ui.home.adapter.ContactsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль для фрагмента с контактами
 *
 * @author Denis Anisimov.
 */
@Module(includes = FireBaseModule.class)
public class ContactsModule {


    @Provides
    @ContactsScope
    ContactsPresenter provideContactsPresenter(ContactRepository contactRepository) {
        return new ContactsPresenter(contactRepository);
    }

    @Provides
    ContactsDataSourceRemote providesContactsDataSource(FirebaseAuth firebaseAuth,
                                                        FirebaseDatabase firebaseDatabase) {
        return new ContactsDataSourceRemote(firebaseAuth, firebaseDatabase);
    }

    @Provides
    ContactRepository providesContactsRepository(ContactsDataSourceRemote contactsDataSourceRemote) {
        return new ContactRepository(contactsDataSourceRemote);
    }


    @Provides
    ContactsAdapter providesAdapter() {
        return new ContactsAdapter();
    }
}
