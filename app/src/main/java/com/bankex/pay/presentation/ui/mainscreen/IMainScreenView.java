package com.bankex.pay.presentation.ui.mainscreen;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * Interface for main screen.
 */
@StateStrategyType(OneExecutionStateStrategy.class)
public interface IMainScreenView extends BaseView {

	/**
	 * Method to show onboarding.
	 */
	void showOnboarding();

	/**
	 * Method to show lock screen.
	 */
	void showLockScreen();

	/**
	 * Method to open import or create wallet screen.
	 */
	void openImportOrCreate();
}
