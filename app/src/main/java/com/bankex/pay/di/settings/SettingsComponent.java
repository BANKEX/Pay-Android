package com.bankex.pay.di.settings;

import com.bankex.pay.presentation.ui.home.SettingsFragment;
import dagger.Subcomponent;

/**
 * Sub component for settings screen.
 */
@Subcomponent(modules = { SettingsModule.class })
@SettingsScope
public interface SettingsComponent {
	@Subcomponent.Builder
	interface Builder {
		SettingsComponent.Builder makeSettingsModule(SettingsModule module);

		SettingsComponent build();
	}

	void inject(SettingsFragment settingsFragment);
}
