package com.bankex.pay.presentation.ui.lockscreen;

import android.support.annotation.StringRes;
import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * Interface for lock screen view.
 */
public interface ILockScreenView extends BaseView {

	/**
	 * Method to unblock screen.
	 */
	void unlock();

	/***
	 * Method to show error message.
	 *
	 * @param message resource id for message string
	 */
	void showMessage(@StringRes int message);

	/**
	 * Method to display sensor state.
	 */
	void setSensorStateMessage(int messageRes);
}
