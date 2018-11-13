package com.bankex.pay.di.lockscreen;

import com.bankex.pay.di.applicationmodules.LittleFingerModule;
import com.bankex.pay.presentation.presenter.LockScreenPresenter;
import com.elegion.littlefinger.LittleFinger;

import dagger.Module;
import dagger.Provides;

/**
 * Model for lock screen.
 */
@Module(includes = LittleFingerModule.class)
public class LockScreenModule {
    @Provides
    @LockScreenScope
    LockScreenPresenter provideLockScreenPresenter(LittleFinger littleFinger) {
        return new LockScreenPresenter(littleFinger);
    }
}
