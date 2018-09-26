package com.bankex.pay;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.bankex.pay.di.application.ApplicationComponent;
import com.bankex.pay.di.application.DaggerApplicationComponent;
import com.bankex.pay.di.module.AnalyticsModule;
import com.bankex.pay.di.module.NavigationModule;
import com.bankex.pay.di.module.NetworkModule;
import com.bankex.pay.di.module.RealmModule;
import com.bankex.pay.di.module.RxModule;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

/**
 * Application класс приложения
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class BankexPayApplication extends MultiDexApplication {
    private static BankexPayApplication sInstance;
    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sApplicationComponent = DaggerApplicationComponent.builder()
                .networkModule(new NetworkModule())
                .rxModule(new RxModule())
                .realmModule(new RealmModule())
                .navigationModule(new NavigationModule())
                .analyticsModule(new AnalyticsModule())
                .build();
        Fabric.with(this, new Crashlytics());
        initRealm();
    }


    private void initRealm() {
        Realm.init(getApplicationContext());
    }

    public static BankexPayApplication getInstance() {
        return sInstance;
    }

    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }
}
