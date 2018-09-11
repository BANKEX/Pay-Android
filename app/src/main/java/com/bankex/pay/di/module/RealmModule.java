package com.bankex.pay.di.module;

import com.bankex.pay.data.realm.DefaultRealmService;
import com.bankex.pay.data.realm.IRealmService;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль представления зависимостей Realm
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class RealmModule {
    @Provides
    IRealmService provideRealmService() {
        return new DefaultRealmService();
    }
}
