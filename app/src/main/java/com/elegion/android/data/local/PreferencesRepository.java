package com.elegion.android.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.elegion.android.data.model.UserProfile;
import com.elegion.android.util.GsonUtils;

/**
 * @author mikhail barannikov
 */
public class PreferencesRepository {
    private static final String KEY_USER_PROFILE = "KEY_USER_PROFILE";
    private static final String KEY_LOGIN_TOKEN = "KEY_LOGIN_TOKEN";

    private static SharedPreferences mPreferences;

    private UserProfile mCurrentUserProfile;

    public PreferencesRepository(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public UserProfile peekCurrentUser() {
        return mCurrentUserProfile;
    }

    @WorkerThread
    public UserProfile setCurrentUser(UserProfile userProfile) {
        mCurrentUserProfile = userProfile;

        if (userProfile == null) {
            mPreferences.edit().remove(KEY_USER_PROFILE).apply();
        } else {
            String userJson = GsonUtils.gson().toJson(userProfile, UserProfile.class);
            mPreferences.edit().putString(KEY_USER_PROFILE, userJson).apply();
        }

        return mCurrentUserProfile;
    }

    @WorkerThread
    public UserProfile getCurrentUserProfile() {
        if (mCurrentUserProfile == null) {
            mCurrentUserProfile = objectFromJsonPreference(KEY_USER_PROFILE, UserProfile.class);
        }

        return mCurrentUserProfile;
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
