package com.bankex.pay.di.addcontact;

import com.bankex.pay.presentation.ui.addcontact.AddContactFragment;
import dagger.Subcomponent;

/**
 * Subcomponent for add contact screen.
 */
@Subcomponent(modules = { AddContactModule.class })
@AddContactScope
public interface AddContactComponent {
	@Subcomponent.Builder
	interface Builder {
		AddContactComponent.Builder makeAddContactModule(AddContactModule module);

		AddContactComponent build();
	}

	void inject(AddContactFragment fragment);
}
