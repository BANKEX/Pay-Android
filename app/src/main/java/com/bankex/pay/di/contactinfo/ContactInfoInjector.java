package com.bankex.pay.di.contactinfo;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Injector for contact full info screen.
 */
public class ContactInfoInjector {
	private static ContactInfoComponent sContactInfoComponent;

	public static ContactInfoComponent getContactsComponent() {
		if (sContactInfoComponent == null) {
			sContactInfoComponent = MainScreenInjector.getMainScreenComponent()
					.plusContactInfoBuilder()
					.makeContactInfotModule(new ContactInfoModule())
					.build();
		}
		return sContactInfoComponent;
	}

	public static void clearContactsComponent() {
		sContactInfoComponent = null;
	}
}

