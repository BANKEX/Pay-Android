package com.bankex.pay.di.setpin;

import com.bankex.pay.di.user.UserComponentInjector;

/**
 * Injector for {@link SetPinComponent}.
 */
public class SetPinInjector {
	private static SetPinComponent sSetPinComponent;

	public static SetPinComponent getSetPinComponent() {
		if (sSetPinComponent == null) {
			sSetPinComponent = UserComponentInjector.getUserComponent()
					.plusSetPinComponentBuilder()
					.makeSetPinModule(new SetPinModule())
					.build();
		}
		return sSetPinComponent;
	}

	public static void clearSetPinComponent() {
		sSetPinComponent = null;
	}
}
