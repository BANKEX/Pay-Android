package com.bankex.pay.di.setpin;

import com.bankex.pay.presentation.view.setpin.SetPinActivity;
import dagger.Subcomponent;

/**
 * Temporary subcomponent for pin code setting
 *
 * @author Denis Anisimov.
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
