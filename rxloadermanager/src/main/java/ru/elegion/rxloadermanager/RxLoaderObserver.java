package ru.elegion.rxloadermanager;

import android.support.annotation.Nullable;
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
    public void onNext(@Nullable T t) {
        //do nothing
    }
}
