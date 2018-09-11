package com.bankex.pay.di.mainscreen;

import com.bankex.pay.di.user.UserComponentInjector;

/**
 * Временный инжектор для варианта "чтобы быстро и работало"
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class MainScreenInjector {

    private static MainScreenComponent sMainScreenComponent;

    public static MainScreenComponent getMainScreenComponent() {
        if (sMainScreenComponent == null) {
            sMainScreenComponent = UserComponentInjector.getUserComponent()
                    .plusMainScreenComponentBuilder()
                    .makeMainScreenModule(new MainScreenModule())
                    .build();
        }
        return sMainScreenComponent;
    }

    public static void clearMainScreenComponent() {
        sMainScreenComponent = null;
    }
}
