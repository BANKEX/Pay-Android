package com.bankex.pay.ui.view.setpin;

import com.bankex.pay.ui.view.base.BaseView;

/**
 * Set pin view interface
 *
 * @author Denis Anisimov.
 */
public interface ISetPinView extends BaseView {

	/**
	 * Method for pin setting
	 */
	void setPin();

	/***
	 * Method showing error message
	 * @param message - error message string
	 */
	void showMessage(String message);

	/**
	 * Method setting sensor state message
	 * @param messageRes - state message id
	 */
	void setSensorStateMessage(int messageRes);
}
