package com.elegion.android.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @author Daniel Serdyukov
 */
public class Lifecycler implements Application.ActivityLifecycleCallbacks {

    private static int sStarted;
    private static int sStopped;

    public static boolean isApplicationVisible() {
        return sStarted > sStopped;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // Do nothing.
    }

    @Override
    public void onActivityStarted(Activity activity) {
        sStarted++;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        // Do nothing.
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // Do nothing.
    }

    @Override
    public void onActivityStopped(Activity activity) {
        sStopped++;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // Do nothing.
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // Do nothing.
    }

}
