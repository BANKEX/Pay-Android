package com.bankex.pay.di.settings;

import com.bankex.pay.ui.navigation.settings.ISettingsRouter;
import com.bankex.pay.ui.navigation.settings.SettingsRouter;
import dagger.Module;
import dagger.Provides;

/**
 * Module for Settings screen
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
