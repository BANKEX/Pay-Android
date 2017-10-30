package com.elegion.android.template.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.elegion.android.template.data.model.Feature;
import com.elegion.android.template.util.GsonUtils;

/**
 * @author mikhail barannikov
 */
public class PreferencesRepository {
    private static final String KEY_FEATURE = "KEY_FEATURE";
    private static final String KEY_LOGIN_TOKEN = "KEY_LOGIN_TOKEN";

    private static SharedPreferences mPreferences;

    private Feature mCurrentFeature;

    public PreferencesRepository(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public Feature peekCurrentUser() {
        return mCurrentFeature;
    }

    @WorkerThread
    public Feature setCurrentUser(Feature feature) {
        mCurrentFeature = feature;

        if (feature == null) {
            mPreferences.edit().remove(KEY_FEATURE).apply();
        } else {
            String featureJson = GsonUtils.gson().toJson(feature, Feature.class);
            mPreferences.edit().putString(KEY_FEATURE, featureJson).apply();
        }

        return mCurrentFeature;
    }

    @WorkerThread
    public Feature getCurrentFeature() {
        if (mCurrentFeature == null) {
            mCurrentFeature = objectFromJsonPreference(KEY_FEATURE, Feature.class);
        }

        return mCurrentFeature;
    }

    @Nullable
    public String getLoginToken() {
        return getPreferences().getString(KEY_LOGIN_TOKEN, null);
    }

    public void setLoginToken(@Nullable String loginToken) {
        getEditor().putString(KEY_LOGIN_TOKEN, loginToken).apply();
    }

    private SharedPreferences getPreferences() {
        return mPreferences;
    }

    private SharedPreferences.Editor getEditor() {
        return mPreferences.edit();
    }

    private <T> T objectFromJsonPreference(@NonNull String key, @NonNull Class<T> tClass) {
        if (!mPreferences.contains(key)) {
            return null;
        }

        String json = mPreferences.getString(key, null);
        return GsonUtils.gson().fromJson(json, tClass);
    }
}
