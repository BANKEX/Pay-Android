package com.bankex.pay.di.addcontact;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Injector for add contact screen.
 */
public class AddContactInjector {
	private static AddContactComponent sAddContactComponent;

	public static AddContactComponent getContactsComponent() {
		if (sAddContactComponent == null) {
			sAddContactComponent = MainScreenInjector.getMainScreenComponent()
					.plusAddContactBuilder()
					.makeAddContactModule(new AddContactModule())
					.build();
		}
		return sAddContactComponent;
	}

	public static void clearContactsComponent() {
		sAddContactComponent = null;
	}
}
