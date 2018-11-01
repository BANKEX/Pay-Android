package com.bankex.pay.di.addcontact;

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
	AddContactPresenter provideAddContactPresenter() {
		return new AddContactPresenter();
	}
}
