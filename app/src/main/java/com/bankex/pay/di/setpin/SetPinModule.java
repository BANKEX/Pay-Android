package com.bankex.pay.di.setpin;

import com.bankex.pay.di.applicationmodules.LittleFingerModule;
import com.bankex.pay.presentation.presenter.SetPinPresenter;
import com.elegion.littlefinger.LittleFinger;
import dagger.Module;
import dagger.Provides;

/**
 * Module for set pin code screen.
 */
@Module(includes = LittleFingerModule.class)
public class SetPinModule {
	@Provides
	@SetPinScope
	SetPinPresenter provideSetPinPresenter(LittleFinger littleFinger) {
		return new SetPinPresenter(littleFinger);
	}
}
