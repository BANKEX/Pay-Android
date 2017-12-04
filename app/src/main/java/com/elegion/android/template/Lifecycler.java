package com.elegion.android.template;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.elegion.android.template.util.HockeyAppUtils;

import net.hockeyapp.android.CrashManager;

/**
 * @author Mikhail Barannikov
 */
public class Lifecycler implements Application.ActivityLifecycleCallbacks {

    private static int sStarted;
    private static int sStopped;

    public static void register(@NonNull Application app) {
        app.registerActivityLifecycleCallbacks(new Lifecycler());
    }

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
        if (!BuildConfig.DEBUG) {
            CrashManager.register(activity, BuildConfig.HOCKEY_APP_ID, HockeyAppUtils.HockeyAppListener.SINGLETON);
        }
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
