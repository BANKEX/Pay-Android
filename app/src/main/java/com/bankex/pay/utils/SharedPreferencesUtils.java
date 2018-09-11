package com.bankex.pay.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Утилитный класс для работы с SharedPreferences
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class SharedPreferencesUtils {

    private static final String SHOWED_ONBOARDING_BEFORE = "SHOWED_ONBOARDING_BEFORE";

    /**
     * Получаем статус - показывали ли онбординг
     *
     * @param context Context
     * @return статус boolean
     */
    public static boolean getOnboardingPreferenceStatus(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(SHOWED_ONBOARDING_BEFORE, false);
    }

    /**
     * Задаем статус - показывали ли онбординг
     *
     * @param context Context
     * @param status  статус boolean
     */
    public static void setOnboardingPreferenceStatus(Context context, boolean status) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit()
                .putBoolean(SHOWED_ONBOARDING_BEFORE, status)
                .apply();
    }

}