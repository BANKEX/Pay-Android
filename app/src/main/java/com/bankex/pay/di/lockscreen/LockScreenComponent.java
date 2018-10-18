package com.bankex.pay.di.lockscreen;

import com.bankex.pay.presentation.ui.lockscreen.LockScreenActivity;
import dagger.Subcomponent;

/**
 * Temporary subcomponent for lock screen
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
