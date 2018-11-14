package com.bankex.pay.di.applicationmodules;

import com.bankex.pay.utils.rx.IRxSchedulersUtils;
import com.bankex.pay.utils.rx.RxSchedulersUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * RxSchedulersUtils module.
 */
@Module
public class RxModule {

    @Provides
    @Singleton
    public IRxSchedulersUtils provideRxSchedulersUtils() {
        return new RxSchedulersUtils();
    }
}
