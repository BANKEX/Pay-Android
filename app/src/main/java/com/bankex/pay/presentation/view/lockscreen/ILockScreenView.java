package com.bankex.pay.presentation.view.lockscreen;

import com.bankex.pay.presentation.view.base.BaseView;

/**
 * Interface for lock screen view
 *
 * @author Denis Anisimov.
 */
public interface ILockScreenView extends BaseView {

    /**
	 * Screen unlock method
     */
    void unlock();

    /***
     * Show error message method
	 *
     * @param message - message to show
     */
    void showMessage(int message);

    /**
     * Method to display sensor status
	 *
     * @param messageRes - message string id
     */
    void setSensorStateMessage(int messageRes);
}
