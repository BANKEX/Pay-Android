package com.bankex.pay.di.module;

import com.bankex.pay.BankexPayApplication;
import com.elegion.littlefinger.LittleFinger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль представления зависимостей LittleFinger
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class LittleFingerModule {

    @Provides
    LittleFinger provideLittleFinger() {
        return new LittleFinger(BankexPayApplication.getInstance().getApplicationContext());
    }
}
