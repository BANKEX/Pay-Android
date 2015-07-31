package com.elegion.android.app;

import android.os.Looper;

import com.squareup.otto.Bus;

import droidkit.concurrent.MainQueue;

/**
 * Created
 * by artem
 * on 25.05.15
 * in Android.
 */
public class Otto {

    //region ---------------------------------------- Variables

    private static final Bus sBus = new Bus();

    //endregion

    //region ---------------------------------------- Constructors


    private Otto() {
    }

    public static Bus getInstance() {
        return sBus;
    }

    //endregion

    //region ---------------------------------------- Static methods

    public static void post(final Object event) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            MainQueue.get().invoke(new Runnable() {
                @Override
                public void run() {
                    sBus.post(event);
                }
            });
        } else {
            sBus.post(event);
        }
    }

    //endregion
}
