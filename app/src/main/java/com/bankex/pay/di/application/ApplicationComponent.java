package com.bankex.pay.di.application;

import android.content.Context;

import com.bankex.pay.di.module.AnalyticsModule;
import com.bankex.pay.di.module.NavigationModule;
import com.bankex.pay.di.module.NetworkModule;
import com.bankex.pay.di.module.RealmModule;
import com.bankex.pay.di.module.RxModule;
import com.bankex.pay.di.user.UserComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Основной компонент Application
 */
@Component(modules = {
        NetworkModule.class,
        RxModule.class,
        RealmModule.class,
        ApplicationModule.class,
        NavigationModule.class,
        AnalyticsModule.class
})
@Singleton
public interface ApplicationComponent {

    UserComponent.Builder plusUserComponent();

    Context getAppContext();
}
