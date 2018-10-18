package com.bankex.pay.di.mainscreen;

import com.bankex.pay.presentation.presenter.mainscreen.MainScreenPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Temporary module for Main screen
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@Module
public class MainScreenModule {

	@Provides
	@MainScreenScope
	MainScreenPresenter provideMainScreenPresenter() {
		return new MainScreenPresenter();
	}
}
