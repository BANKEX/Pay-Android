package com.bankex.pay.di.settings;

import com.bankex.pay.presentation.navigation.settings.ISettingsRouter;
import com.bankex.pay.presentation.navigation.settings.SettingsRouter;
import dagger.Module;
import dagger.Provides;

/**
 * Module for settings screen.
 */
@Module
public class SettingsModule {
	@Provides
	@SettingsScope
	ISettingsRouter provideSettingsRouter() {
		return new SettingsRouter();
	}
}
