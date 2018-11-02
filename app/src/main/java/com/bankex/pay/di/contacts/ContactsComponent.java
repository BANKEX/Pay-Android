package com.bankex.pay.di.contacts;

import com.bankex.pay.presentation.ui.contacts.ContactsFragment;

import dagger.Subcomponent;

/**
 * Subcomponent for contacts screen.
 */
@Subcomponent(modules = { ContactsModule.class })
@ContactsScope
public interface ContactsComponent {
	@Subcomponent.Builder
	interface Builder {
		ContactsComponent.Builder makeContactsModule(ContactsModule module);

		ContactsComponent build();
	}

	void inject(ContactsFragment fragment);
}
