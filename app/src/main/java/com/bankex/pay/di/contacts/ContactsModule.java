package com.bankex.pay.di.contacts;

import com.bankex.pay.presentation.presenter.contacts.ContactsPresenter;

import dagger.Module;
import dagger.Provides;

@Module()
public class ContactsModule {

    @Provides
    @ContactsScope
    ContactsPresenter provideContactsPresenter() {
        return new ContactsPresenter();
    }
}
