package com.elegion.android.app;

import android.app.Activity;

import com.elegion.android.BuildConfig;

import net.hockeyapp.android.CrashManager;


/**
 * @author Daniel Serdyukov
 */
public class FlavoredLifecycler extends Lifecycler {

    @Override
    public void onActivityResumed(Activity activity) {
        CrashManager.register(activity, BuildConfig.HOCKEY_APP_ID);
    }
}
