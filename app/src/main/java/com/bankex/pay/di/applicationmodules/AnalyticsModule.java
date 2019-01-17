package com.bankex.pay.di.applicationmodules;

import com.bankex.pay.domain.analytics.AnalyticsManager;
import com.bankex.pay.domain.analytics.IAnalyticsManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Analytics module.
 */
@Module
public class AnalyticsModule {
    @Provides
    @Singleton
    IAnalyticsManager provideAnalyticsManager() {
        return new AnalyticsManager();
    }
}
