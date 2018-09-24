package com.bankex.pay.di.setpin;

import com.bankex.pay.di.module.LittleFingerModule;
import com.bankex.pay.presentation.presenter.setpin.SetPinPresenter;
import com.elegion.littlefinger.LittleFinger;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль для экрана установки пин кода предоставляющий перезнтер
 * @author Denis Anisimov.
 */
@Module(includes = LittleFingerModule.class)
public class SetPinModule {

    @Provides
    @SetPinScope
    SetPinPresenter provideSetPinPresenter(LittleFinger littleFinger) {
        return new SetPinPresenter(littleFinger);
    }
}
