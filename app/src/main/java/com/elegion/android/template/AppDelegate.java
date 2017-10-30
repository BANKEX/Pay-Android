package com.elegion.android.template;

import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import timber.log.Timber;

/**
 * @author Mikhail Barannikov
 */
public class AppDelegate extends MultiDexApplication {
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
