package com.elegion.android;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import timber.log.Timber;

/**
 * @author Mikhail Barannikov
 */
public class AppDelegate extends Application {
    private static final String PROD_BUILD_TYPE = "live";

    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
        }
        if (isTestBuild()) {
            Timber.plant(new Timber.DebugTree());
        }

        sApplicationContext = getApplicationContext();

        Lifecycler.register(this);
    }

    public static Context getAppContext() {
        return sApplicationContext;
    }

    public static boolean isTestBuild() {
        return !BuildConfig.BUILD_TYPE.equals(PROD_BUILD_TYPE);
    }
}
