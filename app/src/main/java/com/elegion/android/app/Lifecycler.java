package com.elegion.android.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import com.elegion.android.BuildConfig;

import droidkit.log.Logger;
import droidkit.util.DynamicException;
import droidkit.util.DynamicMethod;

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
        if (!TextUtils.isEmpty(BuildConfig.HOCKEY_APP_ID)) {
            try {
                DynamicMethod.invokeStatic(
                        "net.hockeyapp.android.CrashManager",
                        "register",
                        activity, BuildConfig.HOCKEY_APP_ID
                );
            } catch (DynamicException e) {
                Logger.error(e);
            }
        }
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
