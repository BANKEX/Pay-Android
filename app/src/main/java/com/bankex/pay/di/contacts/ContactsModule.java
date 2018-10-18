package com.bankex.pay.di.contacts;

import com.bankex.pay.presentation.presenter.contacts.ContactsPresenter;
import com.bankex.pay.presentation.ui.navigation.contacts.ContactsRouter;
import com.bankex.pay.presentation.ui.navigation.contacts.IContactsRouter;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль экрана контактов
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
@Module
public class ContactsModule {

    @Provides
    @ContactsScope
    IContactsRouter provideContactsRouter(){
        return new ContactsRouter();
    }

    @Provides
    @ContactsScope
    ContactsPresenter provideContactsPresenter(){
        return new ContactsPresenter();
    }
}
