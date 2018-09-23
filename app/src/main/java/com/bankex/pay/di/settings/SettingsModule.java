package com.bankex.pay.di.settings;

import com.bankex.pay.presentation.ui.navigation.settings.ISettingsRouter;
import com.bankex.pay.presentation.ui.navigation.settings.SettingsRouter;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль экрана настроек
 *
 * @author Pavel Apanovskiy on 19.09.2018.
 */
@Module
public class SettingsModule {

    @Provides
    @SettingsScope
    ISettingsRouter provideSettingsRouter() {
        return new SettingsRouter();
    }
}
