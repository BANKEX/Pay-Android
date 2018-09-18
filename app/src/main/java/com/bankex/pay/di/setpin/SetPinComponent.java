package com.bankex.pay.di.setpin;

import com.bankex.pay.presentation.ui.setpin.SetPinActivity;

import dagger.Subcomponent;

/**
 * Временный сабкомпонент для варианта "чтобы быстро и работало"
 *
 * @author Denis Anisimov.
 */
@Subcomponent(modules = {SetPinModule.class})
@SetPinScope
public interface SetPinComponent {

    @Subcomponent.Builder
    interface Builder {
        SetPinComponent.Builder makeSetPinModule(SetPinModule module);

        SetPinComponent build();
    }

    void inject(SetPinActivity activity);
}
