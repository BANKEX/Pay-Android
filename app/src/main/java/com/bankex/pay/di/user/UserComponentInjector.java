package com.bankex.pay.di.user;

import com.bankex.pay.BankexPayApplication;

/**
 * Инжектор компоненты послелогинной зоны
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class UserComponentInjector {

    private static UserComponent sUserComponent;

    public static UserComponent getUserComponent() {
        if (sUserComponent == null) {
            sUserComponent = BankexPayApplication
                    .getApplicationComponent()
                    .plusUserComponent()
                    .userModule(new UserModule())
                    .build();
        }
        return sUserComponent;
    }

    public static void clearUserComponent() {
        sUserComponent = null;
    }
}
