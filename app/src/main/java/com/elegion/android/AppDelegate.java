package com.elegion.android;

import android.app.Application;
import android.os.StrictMode;

import com.elegion.android.app.Lifecycler;

/**
 * @author Daniel Serdyukov
 */
public class AppDelegate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
        }
        Lifecycler.register(this);
    }

}
