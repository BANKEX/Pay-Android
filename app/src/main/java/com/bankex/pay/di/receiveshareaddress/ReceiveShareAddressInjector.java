package com.bankex.pay.di.receiveshareaddress;

import com.bankex.pay.di.user.UserComponentInjector;

/**
 * Injector for {@link ReceiveShareAddressComponent}
 *
 * @author Pavel Apanovskiy on 30/09/2018.
 */
public class ReceiveShareAddressInjector {

	private static ReceiveShareAddressComponent sReceiveShareAddressComponent;

	public static ReceiveShareAddressComponent getSettingsComponent() {
		if (sReceiveShareAddressComponent == null) {
			sReceiveShareAddressComponent = UserComponentInjector.getUserComponent()
					.plusReceiveComponent()
					.makeReceiveModule(new ReceiveShareAddressModule())
					.build();
		}
		return sReceiveShareAddressComponent;
	}

	public static void clearReceiveComponent() {
		sReceiveShareAddressComponent = null;
	}
}
