package com.bankex.pay.di.contacts;

import com.bankex.pay.domain.interactor.IContactsInteractor;
import com.bankex.pay.presentation.navigation.contacts.ContactsRouter;
import com.bankex.pay.presentation.navigation.contacts.IContactsRouter;
import com.bankex.pay.presentation.presenter.ContactsPresenter;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;
import dagger.Module;
import dagger.Provides;

/**
 * Module for contacts list screen.
 */
@Module
public class ContactsModule {
	@Provides
	@ContactsScope
	IContactsRouter provideContactsRouter() {
		return new ContactsRouter();
	}

	@Provides
	@ContactsScope
	ContactsPresenter provideContactsPresenter(IContactsInteractor contactsInteractor, IRxSchedulersUtils rxSchedulersUtils) {
		return new ContactsPresenter(contactsInteractor, rxSchedulersUtils);
	}
}
