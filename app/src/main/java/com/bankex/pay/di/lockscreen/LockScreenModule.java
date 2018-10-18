package com.bankex.pay.di.lockscreen;

import com.bankex.pay.di.module.LittleFingerModule;
import com.bankex.pay.presentation.presenter.lockscreen.LockScreenPresenter;
import com.elegion.littlefinger.LittleFinger;

import dagger.Module;
import dagger.Provides;

/**
 * Module for lock screen
 *
 * @author Denis Anisimov.
 */
@Module(includes = LittleFingerModule.class)
public class LockScreenModule {

	@Provides
	@LockScreenScope
	LockScreenPresenter provideLockScreenPresenter(LittleFinger littleFinger) {
		return new LockScreenPresenter(littleFinger);
	}
}
