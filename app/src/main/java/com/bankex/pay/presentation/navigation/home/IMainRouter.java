package com.bankex.pay.presentation.navigation.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import com.bankex.pay.presentation.ui.base.BaseFragment;

/**
 * Provides methods to navigate to the different screens in the application.
 */
public interface IMainRouter {

	/**
	 * Method to run wallet tab.
	 *
	 * @param activity activity that hold fragment
	 */
	void goToWalletTab(FragmentActivity activity);

	/**
	 * Method to run transactions history tab.
	 *
	 * @param activity activity that hold fragment
	 */
	void goToHistoryTab(FragmentActivity activity);

	/**
	 * Method to run user contacts tab.
	 *
	 * @param activity activity that hold fragment
	 */
	void goToContactsTab(FragmentActivity activity);

	/**
	 * Method to open settings tab.
	 *
	 * @param activity activity that hold fragment
	 */
	void goToSettingsTab(FragmentActivity activity);

	/**
	 * Method to open import or create wallet screen.
	 *
	 * @param context context
	 */
	void openImportOrCreate(Context context);
}
