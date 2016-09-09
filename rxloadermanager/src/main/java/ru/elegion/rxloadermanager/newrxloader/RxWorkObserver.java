package ru.elegion.rxloadermanager.newrxloader;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import rx.Observer;
import rx.Subscription;

/**
 * @author Nikita Bumakov
 */
public class RxWorkObserver<T> implements Observer<T>, Subscription {

    @NonNull
    private WeakReference<RxLoaderObserver<T>> mObserverReference;

    @Nullable
    private Subscription mSubscription;
    private boolean mIsComplete;
    private boolean mIsError;
    private Throwable mError;
    private T mLastValue;

    RxWorkObserver(@NonNull RxLoaderObserver<T> loaderObserver) {
        mObserverReference = new WeakReference<>(loaderObserver);
        init();
    }

    private void init() {
        RxLoaderObserver<T> observer = mObserverReference.get();
        if (observer == null){
            return;
        }

        if (!(mIsComplete || mIsError)) {
            observer.onStarted();
        }

        if (mLastValue != null) {
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
        Observer<T> subscriber = mObserverReference.get();
        if (subscriber != null){
            subscriber.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        mIsError = true;
        mError = e;
        Observer<T> subscriber = mObserverReference.get();
        if (subscriber != null){
            subscriber.onError(e);
        }
    }

    @Override
    public void onNext(T t) {
        mLastValue = t;
        Observer<T> subscriber = mObserverReference.get();
        if (subscriber != null){
            subscriber.onNext(t);
        }
    }

    @Override
    public void unsubscribe() {
        mObserverReference.clear();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    @Override
    public boolean isUnsubscribed() {
        Observer<T> subscriber = mObserverReference.get();
        return subscriber == null;
    }

    public void clear() {
        unsubscribe();
        mIsComplete = false;
        mIsError = false;
        mError = null;
        mLastValue = null;
    }
}
