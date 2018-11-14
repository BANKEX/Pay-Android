package com.bankex.pay.presentation.navigation.importorcreate;

import android.content.Context;

/**
 * Router to navigate from import or create wallet screen.
 */
public interface IImportWalletRouter {

	/**
	 * Method to open wallet screen fragment.
	 *
	 * @param context {@link Context}
	 */
	void openImportWalletScreen(Context context);

	/**
	 * Method to open create wallet screen.
	 *
	 * @param context {@link Context}
	 */
	void openCreateWalletScreen(Context context);

	/**
	 * Method to start MainActivity.
	 *
	 * @param context {@link Context}
	 */
	void startMainActivityScreen(Context context);
}
