package com.bankex.pay.presentation.presenter.mainscreen;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.view.mainscreen.IMainScreenView;
import com.bankex.pay.presentation.view.mainscreen.MainScreenActivity;
import com.bankex.pay.utils.preferences.SharedPreferencesUtils;

/**
 * Presenter for Main screen activity {@link MainScreenActivity}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@InjectViewState
public class MainScreenPresenter extends BasePresenter<IMainScreenView> {
	/**
	 * Checking if onboarding screen has been shown before
	 */
	// TODO probably better put into the model
	public void checkOnboardingStatus() {
		boolean onboardingPreferenceStatus = SharedPreferencesUtils.getOnboardingPreferenceStatus();

		if (!onboardingPreferenceStatus) {
			getViewState().showOnboarding();
		} else {
			getViewState().showLockScreen();
		}
	}
}
