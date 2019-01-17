package com.bankex.pay.presentation.navigation.wallet;

import android.content.Context;

/**
 * Router to navigate from import or create wallet screen.
 */
public interface IWalletRouter {

	/**
	 * Method to open impoet or create wallet screen.
	 *
	 * @param context {@link Context}
	 */
	void openImportOrCreate(Context context);
}
