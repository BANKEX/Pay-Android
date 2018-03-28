package com.elegion.android.template

import android.content.Context
import android.os.StrictMode
import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate

import timber.log.Timber

class AppDelegate : MultiDexApplication() {

    override fun onCreate() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate()

        appContext = applicationContext

        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
            StrictMode.setVmPolicy(
                    StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                            .penaltyLog().build()
            )
        }
        if (isTestBuild) {
            Timber.plant(Timber.DebugTree())
        }

        Lifecycler.register(this)
    }

    companion object {
        @JvmStatic
        private val RELEASE_BUILD_TYPE = "release"

        lateinit var appContext: Context
            private set

        val isTestBuild: Boolean
            get() = BuildConfig.BUILD_TYPE != RELEASE_BUILD_TYPE
    }
}
