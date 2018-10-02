package com.bankex.pay.di.receive;

import com.bankex.pay.di.user.UserComponentInjector;

/**
 * Инжектор компонента {@link ReceiveComponent}
 *
 * @author Pavel Apanovskiy on 30/09/2018.
 */
public class ReceiveInjector {

    private static ReceiveComponent sReceiveComponent;

    public static ReceiveComponent getSettingsComponent() {
        if (sReceiveComponent == null) {
            sReceiveComponent = UserComponentInjector.getUserComponent()
                    .plusReceiveComponent()
                    .makeReceiveModule(new ReceiveModule())
                    .build();
        }
        return sReceiveComponent;
    }

    public static void clearReceiveComponent() {
        sReceiveComponent = null;
    }
}
