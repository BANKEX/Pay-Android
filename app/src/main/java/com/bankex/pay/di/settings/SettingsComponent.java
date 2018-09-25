package com.bankex.pay.di.settings;

import com.bankex.pay.presentation.ui.home.SettingsFragment;

import dagger.Subcomponent;

/**
 * Сабкомпонент экрана настроек
 *
 * @author Pavel Apanovskiy on 19.09.2018.
 */
@Subcomponent(modules = {SettingsModule.class})
@SettingsScope
public interface SettingsComponent {

    @Subcomponent.Builder
    interface Builder {
        SettingsComponent.Builder makeSettingsModule(SettingsModule module);

        SettingsComponent build();
    }

    void inject(SettingsFragment settingsFragment);
}
