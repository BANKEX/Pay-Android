package com.bankex.pay.di.setpin;

import com.bankex.pay.di.user.UserComponentInjector;

/**
 * Инжектор для компонента установки пин кода
 * @author Denis Anisimov.
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
