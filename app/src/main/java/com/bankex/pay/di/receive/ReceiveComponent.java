package com.bankex.pay.di.receive;

import dagger.Subcomponent;

/**
 * Сабкомпонент экрана получения
 *
 * @author Pavel Apanovskiy on 30/09/2018.
 */
@Subcomponent(modules = {ReceiveModule.class})
@ReceiveScope
public interface ReceiveComponent {

    @Subcomponent.Builder
    interface Builder {
        ReceiveComponent.Builder makeReceiveModule(ReceiveModule module);

        ReceiveComponent build();
    }
}
