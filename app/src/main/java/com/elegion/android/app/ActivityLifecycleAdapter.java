package com.elegion.android.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import com.elegion.android.BuildConfig;

import java.lang.reflect.InvocationTargetException;

import droidkit.log.LogLevel;
import droidkit.log.Logger;
import droidkit.util.DynamicMethod;

/**
 * @author Daniel Serdyukov
 */
public class ActivityLifecycleAdapter implements Application.ActivityLifecycleCallbacks {

    public static final Logger LOGGER = Logger.getLogger("ActivityLifecycleAdapter");

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (!TextUtils.isEmpty(BuildConfig.HOCKEY_APP_ID)) {
            try {
                final Class<?> hockey = Class.forName("net.hockeyapp.android.CrashManager");
                DynamicMethod.invoke(hockey, "", "");
            } catch (ClassNotFoundException | InvocationTargetException e) {
                LOGGER.log(LogLevel.ERROR, e);
            }
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

}
