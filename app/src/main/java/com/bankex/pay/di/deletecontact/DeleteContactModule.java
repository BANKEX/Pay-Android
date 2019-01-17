package com.bankex.pay.di.deletecontact;

import com.bankex.pay.domain.interactor.IContactsInteractor;
import com.bankex.pay.presentation.presenter.DeleteContactPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Module for custom delete contact dialog.
 */
@Module
public class DeleteContactModule {
	@Provides
	@DeleteContactScope
	DeleteContactPresenter provideDeleteContactPresenter(IContactsInteractor contactsInteractor) {
		return new DeleteContactPresenter(contactsInteractor);
	}
}
