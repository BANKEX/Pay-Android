package com.bankex.pay.di.contacts;

import android.arch.lifecycle.Lifecycle;

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

    private final Lifecycle lifecycle;

    public ContactsModule(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    @Provides
    @ContactsScope
    ContactsPresenter provideContactsPresenter() {
        return new ContactsPresenter(lifecycle);
    }

    @Provides
    ContactsDataSourceRemote providesContactsDataSource(FirebaseAuth firebaseAuth,
                                                        FirebaseDatabase firebaseDatabase) {
        return new ContactsDataSourceRemote(firebaseAuth, firebaseDatabase);
    }


    @Provides
    ContactsAdapter providesAdapter() {
        return new ContactsAdapter();
    }
}
