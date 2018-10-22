package com.bankex.pay.di.setpin;

import com.bankex.pay.di.module.LittleFingerModule;
import com.bankex.pay.ui.presenter.setpin.SetPinPresenter;
import com.elegion.littlefinger.LittleFinger;
import dagger.Module;
import dagger.Provides;

/**
 * Module that provides Presenter for for pin code setting
 *
 * @author Denis Anisimov.
 */
@Module(includes = LittleFingerModule.class)
public class SetPinModule {

	@Provides
	@SetPinScope
	SetPinPresenter provideSetPinPresenter(LittleFinger littleFinger) {
		return new SetPinPresenter(littleFinger);
	}
}
