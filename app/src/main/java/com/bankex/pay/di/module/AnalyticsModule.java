package com.bankex.pay.di.module;

import com.bankex.pay.domain.AnalyticsManager;
import com.bankex.pay.domain.IAnalyticsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class AnalyticsModule {

    @Provides
    @Singleton
    IAnalyticsManager provideAnalyticsManager() {
        return new AnalyticsManager();
    }
}
