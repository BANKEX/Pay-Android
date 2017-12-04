package com.elegion.android.template.util;

import com.elegion.android.template.BuildConfig;

import net.hockeyapp.android.CrashManagerListener;
import net.hockeyapp.android.ExceptionHandler;

import timber.log.Timber;

/**
 * @author Mike
 */
public class HockeyAppUtils {
    /**
     * Saves exception with stacktrace to HockeyApp
     * @param t
     */
    public static void saveException(Throwable t) {
        if (BuildConfig.DEBUG) {
            Timber.e(t, "Will be saved to HockeyApp in release mode");
        } else {
            ExceptionHandler.saveException(t, Thread.currentThread(), HockeyAppListener.SINGLETON);
        }
    }

    public static final class HockeyAppListener extends CrashManagerListener {
        public static final HockeyAppListener SINGLETON = new HockeyAppListener();

        private HockeyAppListener() {
        }

        @Override
        public boolean shouldAutoUploadCrashes() {
            return true;
        }
    }
}
