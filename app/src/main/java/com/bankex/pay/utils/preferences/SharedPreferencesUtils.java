package com.bankex.pay.utils.preferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.bankex.pay.BankexPayApplication;

/**
 * Utility class to work with SharedPreferences class.
 */
public class SharedPreferencesUtils {
	private static final String IS_ONBOARDING_DISPLAYED = "IS_ONBOARDING_DISPLAYED";
	private static final String KEY_PIN = "KEY_PIN";
	private static final String KEY_ENCODED_PIN = "KEY_ENCODED_PIN";

	private static SharedPreferences getDefaultSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(BankexPayApplication.getInstance().getApplicationContext());
	}

	/**
	 * Method to get onboarding display status.
	 *
	 * @return boolean status if onboarding displayed before
	 */
	public static boolean getOnboardingPreferenceStatus() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.getBoolean(IS_ONBOARDING_DISPLAYED, false);
	}

	/**
	 * Method to set onboarding display status.
	 *
	 * @param status boolean value to set
	 */
	public static void setOnboardingPreferenceStatus(boolean status) {
		SharedPreferences preferences = getDefaultSharedPreferences();
		preferences
				.edit()
				.putBoolean(IS_ONBOARDING_DISPLAYED, status)
				.apply();
	}

	/**
	 * Method to get pin code encoding status.
	 *
	 * @return boolean value that shows if pin code is encoded
	 */
	public static boolean isPinEncoded() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.contains(KEY_ENCODED_PIN);
	}

	/**
	 * Method to get pin saved status.
	 *
	 * @return boolean value that shows if pin code was saved
	 */
	public static boolean isPinSaved() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.contains(KEY_PIN);
	}

	/**
	 * Method to get pin code.
	 *
	 * @return String value pin code
	 */
	public static String pin() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.getString(KEY_PIN, null);
	}

	/**
	 * Method to set pin code.
	 *
	 * @param pin String value to set
	 */
	public static void setPin(String pin) {
		SharedPreferences preferences = getDefaultSharedPreferences();
		preferences.edit().putString(KEY_PIN, pin).apply();
	}

	/**
	 * Method to get encoded fingerprint pin code.
	 *
	 * @return String value pin code
	 */
	public static String encodedPin() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.getString(KEY_ENCODED_PIN, null);
	}

	/**
	 * Method to set  encoded fingerprint pin code.
	 *
	 * @param pin String value to set
	 */
	public static void setEncodedPin(String pin) {
		SharedPreferences preferences = getDefaultSharedPreferences();
		preferences.edit().putString(KEY_ENCODED_PIN, pin).apply();
	}
}