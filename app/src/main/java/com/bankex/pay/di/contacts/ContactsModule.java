package com.bankex.pay.di.contacts;

import com.bankex.pay.data.reporitories.ContactsDataSourceRemote;
import com.bankex.pay.di.module.FireBaseModule;
import com.bankex.pay.presentation.presenter.contacts.ContactsPresenter;
import com.bankex.pay.presentation.ui.home.adapter.ContactsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

@Module(includes = FireBaseModule.class)
public class ContactsModule {

    @Provides
    @ContactsScope
    ContactsPresenter provideContactsPresenter() {
        return new ContactsPresenter();
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
