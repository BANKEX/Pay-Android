package com.bankex.pay.di.contactinfo;

import com.bankex.pay.domain.interactor.IContactsInteractor;
import com.bankex.pay.presentation.presenter.ContactInfoPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Module for full contact info screen.
 */
@Module
public class ContactInfoModule {
	@Provides
	@ContactInfoScope
	ContactInfoPresenter provideContactInfoPresenter(IContactsInteractor mContactsInteractor) {
		return new ContactInfoPresenter(mContactsInteractor);
	}
}
