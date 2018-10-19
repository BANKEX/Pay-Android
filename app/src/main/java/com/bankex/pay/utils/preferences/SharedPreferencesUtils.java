package com.bankex.pay.utils.preferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.bankex.pay.BankexPayApplication;

/**
 * Utility class to work with SharedPreferences
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class SharedPreferencesUtils {
	private static final String IS_ONBOARDING_SHOWED_BEFORE = "IS_ONBOARDING_SHOWED_BEFORE";
	private static final String KEY_PIN = "KEY_PIN";
	private static final String KEY_ENCODED_PIN = "KEY_ENCODED_PIN";

	private static SharedPreferences getDefaultSharedPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(BankexPayApplication.getInstance().getApplicationContext());
	}

	/**
	 * Getter for variable that indicates if onboarding has been showed before
	 *
	 * @return onboarding status boolean
	 */
	public static boolean getOnboardingPreferenceStatus() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.getBoolean(IS_ONBOARDING_SHOWED_BEFORE, false);
	}

	/**
	 * Setter for variable that indicates if onboarding was shown before
	 *
	 * @param status onboarding status boolean
	 */
	public static void setOnboardingPreferenceStatus(boolean status) {
		SharedPreferences preferences = getDefaultSharedPreferences();
		preferences.edit()
				.putBoolean(IS_ONBOARDING_SHOWED_BEFORE, status)
				.apply();
	}

	/**
	 * Getter for variable that shows if pin was encoded
	 *
	 * @return pin status boolean
	 */
	public static boolean isPinEncoded() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.contains(KEY_ENCODED_PIN);
	}

	/**
	 * Getter for variable that shows if was saved
	 *
	 * @return pin saved status boolean
	 */
	public static boolean isPinSaved() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.contains(KEY_PIN);
	}

	/**
	 * Getter for saved pin
	 *
	 * @return pin String
	 */
	public static String pin() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.getString(KEY_PIN, null);
	}

	/**
	 * Getter for encoded pin
	 *
	 * @return encoded pin String
	 */
	public static String encodedPin() {
		SharedPreferences preferences = getDefaultSharedPreferences();
		return preferences.getString(KEY_ENCODED_PIN, null);
	}

	/**
	 * Setter for encoded pin
	 */
	public static void setEncodedPin(String pin) {
		SharedPreferences preferences = getDefaultSharedPreferences();
		preferences.edit().putString(KEY_ENCODED_PIN, pin).apply();
	}

	/**
	 * Setter for saved pin
	 */
	public static void setPin(String pin) {
		SharedPreferences preferences = getDefaultSharedPreferences();
		preferences.edit().putString(KEY_PIN, pin).apply();
	}
}