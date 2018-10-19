package com.bankex.pay.presentation.view.setpin;

import com.bankex.pay.presentation.view.base.BaseView;

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
