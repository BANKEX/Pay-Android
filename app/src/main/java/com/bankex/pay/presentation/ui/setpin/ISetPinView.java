package com.bankex.pay.presentation.ui.setpin;

import android.support.annotation.StringRes;
import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * Interface for setting custom pin to open application.
 */
public interface ISetPinView extends BaseView {

	/**
	 * Method to set pin code.
	 */
	void setPin();

	/***
	 * Method to show message.
	 *
	 * @param message String message to show
	 */
	void showMessage(String message);

	/**
	 * Method to display sensor state.
	 *
	 * @param messageRes message id from resources
	 */
	void setSensorStateMessage(@StringRes int messageRes);
}
