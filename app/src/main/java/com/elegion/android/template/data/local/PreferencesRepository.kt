package com.elegion.android.template.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.annotation.WorkerThread

import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.util.GsonUtils

class PreferencesRepository(context: Context) {

    private var mCurrentFeature: Feature? = null

    val currentFeature: Feature?
        @WorkerThread
        get() {
            if (mCurrentFeature == null) {
                mCurrentFeature = objectFromJsonPreference(KEY_FEATURE, Feature::class.java)
            }

            return mCurrentFeature
        }

    var loginToken: String?
        get() = preferences.getString(KEY_LOGIN_TOKEN, null)
        set(loginToken) = editor.putString(KEY_LOGIN_TOKEN, loginToken).apply()

    private val preferences: SharedPreferences
        get() = mPreferences

    private val editor: SharedPreferences.Editor
        get() = mPreferences.edit()

    init {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
    }

    @WorkerThread
    fun setCurrentUser(feature: Feature?): Feature? {
        mCurrentFeature = feature

        if (feature == null) {
            mPreferences.edit().remove(KEY_FEATURE).apply()
        } else {
            val featureJson = GsonUtils.gson().toJson(feature, Feature::class.java)
            mPreferences.edit().putString(KEY_FEATURE, featureJson).apply()
        }

        return mCurrentFeature
    }

    private fun <T> objectFromJsonPreference(key: String, tClass: Class<T>): T? {
        if (!mPreferences.contains(key)) {
            return null
        }

        val json = mPreferences.getString(key, null)
        return GsonUtils.gson().fromJson(json, tClass)
    }

    companion object {
        private val KEY_FEATURE = "KEY_FEATURE"
        private val KEY_LOGIN_TOKEN = "KEY_LOGIN_TOKEN"

        private var mPreferences: SharedPreferences
    }
}
