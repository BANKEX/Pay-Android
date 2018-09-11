package com.bankex.pay.di.module;

import com.bankex.pay.utils.rx.IRxSchedulersUtils;
import com.bankex.pay.utils.rx.RxSchedulersUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль для предоставления RxSchedulersUtils
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class RxModule {

    @Provides
    @Singleton
    public IRxSchedulersUtils provideRxSchedulersUtils() {
        return new RxSchedulersUtils();
    }
}
