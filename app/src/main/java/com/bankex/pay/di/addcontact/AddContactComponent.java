package com.bankex.pay.di.addcontact;

import com.bankex.pay.di.contacts.ContactsScope;
import com.bankex.pay.presentation.ui.view.addcontact.AddContactFragment;
import dagger.Subcomponent;

/**
 * Subcomponent for add contact screen.
 */
@Subcomponent(modules = { AddContactModule.class })
@ContactsScope
public interface AddContactComponent {
	@Subcomponent.Builder
	interface Builder {
		AddContactComponent.Builder makeAddCOntactsModule(AddContactModule module);

		AddContactComponent build();
	}

	void inject(AddContactFragment fragment);
}
