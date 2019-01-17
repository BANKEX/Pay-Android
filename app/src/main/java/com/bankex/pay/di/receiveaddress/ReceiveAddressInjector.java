package com.bankex.pay.di.receiveaddress;

import com.bankex.pay.di.user.UserComponentInjector;

/**
 * Injector for {@link ReceiveAddressComponent}.
 */
public class ReceiveAddressInjector {
	private static ReceiveAddressComponent sReceiveComponent;

	public static ReceiveAddressComponent getSettingsComponent() {
		if (sReceiveComponent == null) {
			sReceiveComponent = UserComponentInjector.getUserComponent()
					.plusReceiveComponent()
					.makeReceiveModule(new ReceiveAddressModule())
					.build();
		}
		return sReceiveComponent;
	}

	public static void clearReceiveComponent() {
		sReceiveComponent = null;
	}
}
