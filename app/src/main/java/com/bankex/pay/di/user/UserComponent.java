package com.bankex.pay.di.user;

import com.bankex.pay.di.mainscreen.MainScreenComponent;
import com.bankex.pay.di.setpin.SetPinComponent;
import com.bankex.pay.di.lockscreen.LockScreenComponent;

import dagger.Subcomponent;

/**
 * Компонент после логинной зоны
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Subcomponent(modules = UserModule.class)
@UserScope
public interface UserComponent {


    @Subcomponent.Builder
    interface Builder {
        UserComponent.Builder userModule(UserModule userModule);

        UserComponent build();
    }

    MainScreenComponent.Builder plusMainScreenComponentBuilder();

    SetPinComponent.Builder plusSetPinComponentBuilder();
    LockScreenComponent.Builder plusLockScreenComponentBuilder();
}
