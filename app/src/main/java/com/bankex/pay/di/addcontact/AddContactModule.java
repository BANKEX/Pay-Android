package com.bankex.pay.di.addcontact;

import com.bankex.pay.presentation.presenter.addcontact.AddContactPresenter;
import com.bankex.pay.presentation.ui.navigation.addcontact.AddContactRouter;
import com.bankex.pay.presentation.ui.navigation.addcontact.IAddContactRouter;
import dagger.Module;
import dagger.Provides;

/**
 * Module for add contact screen.
 */
@Module
public class AddContactModule {
	@Provides
	@AddContactScope
	IAddContactRouter provideAddContactsRouter() {
		return new AddContactRouter();
	}

	@Provides
	@AddContactScope
	AddContactPresenter provideAddContactPresenter() {
		return new AddContactPresenter();
	}
}
