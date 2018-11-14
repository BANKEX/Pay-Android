package com.bankex.pay.di.applicationmodules;

import com.bankex.pay.BankexPayApplication;
import com.elegion.littlefinger.LittleFinger;
import dagger.Module;
import dagger.Provides;

/**
 * Module for LittleFinger (fingerprint).
 */
@Module
public class LittleFingerModule {
	@Provides
	LittleFinger provideLittleFinger() {
		return new LittleFinger(BankexPayApplication.getInstance().getApplicationContext());
	}
}
