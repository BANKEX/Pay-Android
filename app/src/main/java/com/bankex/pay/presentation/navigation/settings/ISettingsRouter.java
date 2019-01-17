package com.bankex.pay.presentation.navigation.settings;

import android.content.Context;

/**
 * Router to navigate from settings tab.
 */
public interface ISettingsRouter {

	/**
	 * Method to open email client with set email address.
	 * If there is not email app on device - message will appear.
	 *
	 * @param context {@link Context}
	 */
	void goToEmail(Context context);

	/**
	 * Method to open Twitter page.
	 *
	 * @param context {@link Context}
	 */
	void goToTwitter(Context context);

	/**
	 * Method to open Facebook page.
	 *
	 * @param context {@link Context}
	 */
	void goToFacebook(Context context);
}
