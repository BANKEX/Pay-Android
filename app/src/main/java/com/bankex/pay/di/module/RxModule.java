package com.bankex.pay.di.module;

import com.bankex.pay.utils.rx.IRxSchedulersUtils;
import com.bankex.pay.utils.rx.RxSchedulersUtils;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Module for RxSchedulersUtils
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class RxModule {

	@Provides
	@Singleton
	public IRxSchedulersUtils provideRxSchedulersUtils() {
		return new RxSchedulersUtils();
	}
}
