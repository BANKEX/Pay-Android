package ru.elegion.rxloadermanager.newrxloader;

import android.util.Log;

import rx.Observer;

/**
 * @author Nikita Bumakov
 */
public class RxLoaderObserver<T> implements Observer<T> {

    private static final String TAG = RxLoaderObserver.class.getSimpleName();

    public void onStarted() {
        //do nothing
    }

    @Override
    public void onCompleted() {
        //do nothing
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.getMessage(), e);
    }

    @Override
    public void onNext(T t) {
        //do nothing
    }
}
