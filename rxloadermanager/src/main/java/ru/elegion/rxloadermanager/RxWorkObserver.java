package ru.elegion.rxloadermanager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.Observer;
import rx.Subscription;

/**
 * @author Nikita Bumakov
 */
public class RxWorkObserver<T> implements Observer<T>, Subscription {

    private static final String TAG = RxWorkObserver.class.getSimpleName();

    @SuppressWarnings("NullableProblems")
    @NonNull
    private Reference<RxLoaderObserver<T>> mObserverReference;

    @Nullable
    private Subscription mSubscription;

    private boolean mIsComplete;

    private boolean mIsError;
    private Throwable mError;

    @Nullable
    private T mLastValue;
    private boolean mLastValueReceived;

    RxWorkObserver(@NonNull RxLoaderObserver<T> loaderObserver) {
        initObserver(loaderObserver);
    }

    void initObserver(@NonNull RxLoaderObserver<T> loaderObserver) {
        mObserverReference = new WeakReference<>(loaderObserver);
        init();
    }

    private void init() {
        RxLoaderObserver<T> observer = mObserverReference.get();
        if (observer == null) {
            return;
        }

        if (!(mIsComplete || mIsError)) {
            observer.onStarted();
        }

        if (mLastValueReceived) {
            observer.onNext(mLastValue);
        }

        if (mIsComplete) {
            observer.onCompleted();
        } else if (mIsError) {
            observer.onError(mError);
        }
    }

    public void setSubscription(@Nullable Subscription subscription) {
        mSubscription = subscription;
    }

    public RxLoaderObserver<T> get() {
        return mObserverReference.get();
    }

    @Override
    public void onCompleted() {
        mIsComplete = true;
        Observer<T> observer = mObserverReference.get();
        if (observer == null) {
            Log.w(TAG, "onCompleted called, but subscriber reference was cleared");
            return;
        }
        observer.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        mIsError = true;
        mError = e;
        Observer<T> observer = mObserverReference.get();
        if (observer == null) {
            Log.w(TAG, "onError called, but subscriber reference was cleared");
            return;
        }
        observer.onError(e);
    }

    @Override
    public void onNext(T t) {
        mLastValue = t;
        mLastValueReceived = true;
        Observer<T> subscriber = mObserverReference.get();
        if (subscriber == null) {
            if (BuildConfig.DEBUG)
                Log.w(TAG, "onNext called, but subscriber reference was cleared. Please check that the activity has a hard reference on RxLoaderObserver");
            return;
        }
        subscriber.onNext(t);
    }

    @Override
    public void unsubscribe() {
        Log.d(TAG, "unsubscribe");
        mObserverReference.clear();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public boolean isUnsubscribed() {
        Observer<T> observer = mObserverReference.get();
        return observer == null;
    }

    public void clear() {
        unsubscribe();
        mIsComplete = false;
        mIsError = false;
        mError = null;
        mLastValueReceived = false;
        mLastValue = null;
    }
}
