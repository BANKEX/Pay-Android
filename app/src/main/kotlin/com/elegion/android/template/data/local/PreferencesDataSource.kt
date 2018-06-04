package com.elegion.android.template.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferencesDataSource(context: Context) {

    private var preferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    var onBoardingFlag: Boolean
        get() = preferences.getBoolean(KEY_ONBOARDING_FLAG, false)
        set(onboardingFlag) = editor.putBoolean(KEY_ONBOARDING_FLAG, onboardingFlag).apply()

    private val editor: SharedPreferences.Editor
        @SuppressLint("CommitPrefEdits")
        get() = preferences.edit()

    companion object {
        private const val KEY_ONBOARDING_FLAG = "KEY_ONBOARDING_FLAG"
    }
}