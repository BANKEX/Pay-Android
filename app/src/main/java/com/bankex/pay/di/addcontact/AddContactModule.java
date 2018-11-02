package com.bankex.pay.di.addcontact;

import com.bankex.pay.domain.interactor.IContactsInteractor;
import com.bankex.pay.presentation.presenter.addcontact.AddContactPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Module for add contact screen.
 */
@Module
public class AddContactModule {
	@Provides
	@AddContactScope
	AddContactPresenter provideAddContactPresenter(IContactsInteractor mContactsInteractor) {
		return new AddContactPresenter(mContactsInteractor);
	}
}
