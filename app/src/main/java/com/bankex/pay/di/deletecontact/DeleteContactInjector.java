package com.bankex.pay.di.deletecontact;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Injector for custom delete contact dialog.
 */
public class DeleteContactInjector {
	private static DeleteContactComponent sDeleteContactComponent;

	public static DeleteContactComponent getsDeleteContactComponent() {
		if (sDeleteContactComponent == null) {
			sDeleteContactComponent = MainScreenInjector.getMainScreenComponent()
					.plusDeleteContactBuilder()
					.makeDeleteContactModule(new DeleteContactModule())
					.build();
		}
		return sDeleteContactComponent;
	}

	public static void clearDeleteContactComponent() {
		sDeleteContactComponent = null;
	}
}
