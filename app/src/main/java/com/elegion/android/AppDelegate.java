package com.elegion.android;

import android.app.Application;
import android.os.StrictMode;

import com.elegion.android.app.ActivityLifecycleAdapter;

import droidkit.log.Logger;

/**
 * @author Daniel Serdyukov
 */
public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
            Logger.setDefaultLogger(Logger
                    .getLogger("AndroidProject")
                    .setEnabled(true));
        }
        registerActivityLifecycleCallbacks(new ActivityLifecycleAdapter());
    }

}
