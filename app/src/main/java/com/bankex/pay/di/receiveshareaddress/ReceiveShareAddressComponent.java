package com.bankex.pay.di.receiveshareaddress;

import dagger.Subcomponent;

/**
 * Subcomponent for Receive/Share Address screen
 *
 * @author Pavel Apanovskiy on 30/09/2018.
 */
@Subcomponent(modules = { ReceiveShareAddressModule.class })
@ReceiveShareAddressScope
public interface ReceiveShareAddressComponent {

	@Subcomponent.Builder
	interface Builder {
		ReceiveShareAddressComponent.Builder makeReceiveModule(ReceiveShareAddressModule module);

		ReceiveShareAddressComponent build();
	}
}
