package com.elegion.android.template.util

import com.elegion.android.template.BuildConfig

import net.hockeyapp.android.CrashManagerListener
import net.hockeyapp.android.ExceptionHandler

import timber.log.Timber

object HockeyAppUtils {
    /**
     * Saves exception with stacktrace to HockeyApp
     * @param t
     */
    @JvmStatic
    fun saveException(t: Throwable) {
        if (BuildConfig.DEBUG) {
            Timber.e(t, "Will be saved to HockeyApp in release mode")
        } else {
            ExceptionHandler.saveException(t, Thread.currentThread(), HockeyAppListener.SINGLETON)
        }
    }

    class HockeyAppListener private constructor() : CrashManagerListener() {

        override fun shouldAutoUploadCrashes(): Boolean {
            return true
        }

        companion object {
            val SINGLETON = HockeyAppListener()
        }
    }
}
