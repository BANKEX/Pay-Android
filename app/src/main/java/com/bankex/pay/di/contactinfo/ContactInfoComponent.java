package com.bankex.pay.di.contactinfo;

import com.bankex.pay.presentation.ui.contactinfo.ContactInfoFragment;
import dagger.Subcomponent;

/**
 * Subcomponent for contact full info screen.
 */
@Subcomponent(modules = { ContactInfoModule.class })
@ContactInfoScope
public interface ContactInfoComponent {
	@Subcomponent.Builder
	interface Builder {
		ContactInfoComponent.Builder makeContactInfoModule(ContactInfoModule module);

		ContactInfoComponent build();
	}

	void inject(ContactInfoFragment fragment);
}
