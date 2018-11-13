package com.bankex.pay.di.setpin;

import com.bankex.pay.presentation.ui.setpin.SetPinActivity;
import dagger.Subcomponent;

/**
 * Sub component for set pin code screen.
 */
@Subcomponent(modules = { SetPinModule.class })
@SetPinScope
public interface SetPinComponent {
	@Subcomponent.Builder
	interface Builder {
		SetPinComponent.Builder makeSetPinModule(SetPinModule module);

		SetPinComponent build();
	}

	void inject(SetPinActivity activity);
}
