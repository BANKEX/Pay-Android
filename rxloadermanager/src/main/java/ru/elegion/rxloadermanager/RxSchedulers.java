package ru.elegion.rxloadermanager;

import android.support.annotation.NonNull;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Daniel Serdyukov
 */
public final class RxSchedulers {

    private RxSchedulers() {
    }

    @NonNull
    public static Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    public static Scheduler main() {
        return AndroidSchedulers.mainThread();
    }

}
