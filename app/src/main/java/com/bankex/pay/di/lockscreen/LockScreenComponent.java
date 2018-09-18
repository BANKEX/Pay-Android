package com.bankex.pay.di.lockscreen;

import com.bankex.pay.presentation.ui.lockscreen.LockScreenActivity;

import dagger.Subcomponent;

/**
 * Временный сабкомпонент для варианта "чтобы быстро и работало"
 *
 * @author Denis Anisimov.
 */
@Subcomponent(modules = LockScreenModule.class)
@LockScreenScope
public interface LockScreenComponent {

    @Subcomponent.Builder
    interface Builder {
        LockScreenComponent.Builder makeLockScreenModule(LockScreenModule module);

        LockScreenComponent build();
    }

    void inject(LockScreenActivity activity);
}
