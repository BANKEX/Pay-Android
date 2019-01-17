package com.bankex.pay.di.receiveaddress;

import dagger.Subcomponent;

/**
 * Sub component for receive address screen.
 */
@Subcomponent(modules = {ReceiveAddressModule.class})
@ReceiveAddressScope
public interface ReceiveAddressComponent {
    @Subcomponent.Builder
    interface Builder {
        ReceiveAddressComponent.Builder makeReceiveModule(ReceiveAddressModule module);

        ReceiveAddressComponent build();
    }
}
