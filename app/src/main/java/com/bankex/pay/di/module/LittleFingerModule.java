package com.bankex.pay.di.module;

import com.bankex.pay.BankexPayApplication;
import com.elegion.littlefinger.LittleFinger;
import dagger.Module;
import dagger.Provides;

/**
 * Dependency module for LittleFinger (finger print)
 *
 * @author Denis Anisimov.
 */
@Module
public class LittleFingerModule {

	@Provides
	LittleFinger provideLittleFinger() {
		return new LittleFinger(BankexPayApplication.getInstance().getApplicationContext());
	}
}
