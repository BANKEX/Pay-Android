package com.elegion.android.template.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class PreferencesRepository(context: Context) {

    private var preferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    var loginToken: String?
        get() = preferences.getString(KEY_LOGIN_TOKEN, null)
        set(loginToken) = editor.putString(KEY_LOGIN_TOKEN, loginToken).apply()

    private val editor: SharedPreferences.Editor
        @SuppressLint("CommitPrefEdits")
        get() = preferences.edit()

    companion object {
        private const val KEY_LOGIN_TOKEN = "KEY_LOGIN_TOKEN"
    }
}