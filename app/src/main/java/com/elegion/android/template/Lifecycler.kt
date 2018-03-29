package com.elegion.android.template

import android.app.Activity
import android.app.Application
import android.os.Bundle

import com.elegion.android.template.util.HockeyAppUtils

import net.hockeyapp.android.CrashManager

open class Lifecycler : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
        // Do nothing.
    }

    override fun onActivityStarted(activity: Activity) {
        sStarted++
        if (!BuildConfig.DEBUG) {
            CrashManager.register(activity, BuildConfig.HOCKEY_APP_ID, HockeyAppUtils.HockeyAppListener.SINGLETON)
        }
    }

    override fun onActivityResumed(activity: Activity) {
        // Do nothing.
    }

    override fun onActivityPaused(activity: Activity) {
        // Do nothing.
    }

    override fun onActivityStopped(activity: Activity) {
        sStopped++
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // Do nothing.
    }

    override fun onActivityDestroyed(activity: Activity) {
        // Do nothing.
    }

    companion object {

        private var sStarted: Int = 0
        private var sStopped: Int = 0

        @JvmStatic
        fun register(app: Application) {
            app.registerActivityLifecycleCallbacks(Lifecycler())
        }

        val isApplicationVisible: Boolean
            get() = sStarted > sStopped
    }

}
