package com.bankex.pay.utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bankex.pay.BankexPayApplication;

/**
 * Утилитный класс для работы с SharedPreferences
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class SharedPreferencesUtils {

    private static final String SHOWED_ONBOARDING_BEFORE = "SHOWED_ONBOARDING_BEFORE";
    private static final String KEY_PIN = "KEY_PIN";
    private static final String KEY_ENCODED_PIN = "KEY_ENCODED_PIN";

    /**
     * Получаем статус - показывали ли онбординг
     *
     * @param context Context
     * @return статус boolean
     */
    public static boolean getOnboardingPreferenceStatus(Context context) {
        SharedPreferences preferences = getDefaultSharedPreferences();
        return preferences.getBoolean(SHOWED_ONBOARDING_BEFORE, false);
    }

    /**
     * Задаем статус - показывали ли онбординг
     *
     * @param context Context
     * @param status  статус boolean
     */
    public static void setOnboardingPreferenceStatus(Context context, boolean status) {
        SharedPreferences preferences = getDefaultSharedPreferences();
        preferences.edit()
                .putBoolean(SHOWED_ONBOARDING_BEFORE, status)
                .apply();
    }


    public static boolean isPinEncoded() {
        SharedPreferences preferences = getDefaultSharedPreferences();
        return preferences.contains(KEY_ENCODED_PIN);
    }

    private static SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(BankexPayApplication.getInstance().getApplicationContext());
    }

    public static boolean isPinSaved() {
        SharedPreferences preferences = getDefaultSharedPreferences();
        return preferences.contains(KEY_PIN);
    }

    public static String pin() {
        SharedPreferences preferences = getDefaultSharedPreferences();
        return preferences.getString(KEY_PIN, null);
    }

    public static String encodedPin() {
        SharedPreferences preferences = getDefaultSharedPreferences();
        return preferences.getString(KEY_ENCODED_PIN, null);
    }

    public static void setEncodedPin(String pin) {
        SharedPreferences preferences = getDefaultSharedPreferences();
        preferences.edit().putString(KEY_ENCODED_PIN, pin).apply();
    }

    public static void setPin(String pin) {
        SharedPreferences preferences = getDefaultSharedPreferences();
        preferences.edit().putString(KEY_PIN, pin).apply();
    }

}