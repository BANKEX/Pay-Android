package com.elegion.android.bankex.util

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.elegion.android.bankex.BuildConfig
import io.fabric.sdk.android.Fabric
import timber.log.Timber

object CrashlyticsUtils {
    private const val UNKNOWN_USER = "unknown_user"
    private const val UNKNOWN_USER_ID = "unknown_user_id"

    @JvmStatic
    fun init(context: Context) {
        Fabric.with(context.applicationContext, Crashlytics())
        Crashlytics.setUserName(UNKNOWN_USER)
        Crashlytics.setUserIdentifier(UNKNOWN_USER_ID)
    }

    /**
     * Saves exception with stacktrace to HockeyApp
     * @param t
     */
    @JvmStatic
    fun logException(t: Throwable) {
        if (BuildConfig.DEBUG) {
            Timber.e(t, "Will be saved to crash manager in release mode")
        } else {
            Crashlytics.logException(t)
        }
    }

    /**
     * Saves logs to crash manager which will be displayed along with the crash
     * It is good to add logs from analytics here. Like button clicks and similar events.
     * @param t
     */
    @JvmStatic
    fun log(msg: String) {
        if (BuildConfig.DEBUG) {
            Timber.i(msg)
        } else {
            Crashlytics.log(msg)
        }
    }
}
