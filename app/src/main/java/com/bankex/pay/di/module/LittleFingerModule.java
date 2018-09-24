package com.bankex.pay.di.module;

import com.bankex.pay.BankexPayApplication;
import com.elegion.littlefinger.LittleFinger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль представления зависимостей LittleFinger
 *
 * @author Denis Anisimov.
 */
@Module
public class LittleFingerModule {

    @Provides
    LittleFinger provideLittleFinger() {
        return new LittleFinger(BankexPayApplication.getInstance().getApplicationContext());
    }
}
