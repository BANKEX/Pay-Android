package ru.elegion.rxloadermanager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import rx.AsyncEmitter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.MainThreadSubscription;
import rx.functions.Action1;

/**
 * @author Nikita Bumakov
 */
public class RxLoader<T> {

    private static final String TAG = RxLifecycleFragment.class.getSimpleName();

    @NonNull
    private Observable<T> mParentObservable;

    @Nullable
    private Observable<T> mChildObservable;

    @Nullable
    private AsyncEmitter<T> mEmitter;

    @Nullable
    private Subscription mSubscription;

    private boolean mHasLastValue = false;

    @Nullable
    private T mLastValue;

    private boolean mHasError = false;

    @Nullable
    private Throwable mError;

    private boolean mIsCompleted = false;

    public RxLoader(@NonNull Observable<T> observable) {
        mParentObservable = observable;
    }

    @NonNull
    Observable<T> getChildObservable() {
        if (mChildObservable == null) {
            mChildObservable = Observable.fromEmitter(new EmitterAction(), AsyncEmitter.BackpressureMode.NONE);
        }
        return mChildObservable;
    }

    void stopEmitting() {
        Log.d(TAG, "stopEmitting");
        mEmitter = null;
        mChildObservable = null;
    }

    void reset() {
        Log.d(TAG, "reset");
        unsubscribe();
        mChildObservable = null;
        mHasLastValue = false;
        mLastValue = null;
        mHasError = false;
        mError = null;
        mIsCompleted = false;
        mEmitter = null;
    }

    private void unsubscribe() {
        Log.d(TAG, "unsubscribe");
        if (mSubscription != null) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }

    private void subscribe() {
        if (mSubscription == null && mError == null && !mIsCompleted) {
            Log.d(TAG, "subscribe");
            mSubscription = mParentObservable.subscribe(new LoaderSubscriber());
        }
    }

    private void emmitError() {
        if (mEmitter == null) {
            return;
        }
        mEmitter.onError(mError);
        mHasError = false;
        mError = null;
    }

    private class EmitterAction implements Action1<AsyncEmitter<T>> {

        @Override
        public void call(@NonNull AsyncEmitter<T> asyncEmitter) {
            mEmitter = asyncEmitter;
            mEmitter.setSubscription(new MainThreadSubscription() {
                @Override
                protected void onUnsubscribe() {
                    RxLoader.this.unsubscribe();
                }
            });

            if (mHasLastValue) {
                mEmitter.onNext(mLastValue);
            }
            if (mIsCompleted) {
                mEmitter.onCompleted();
            } else if (mHasError) {
                emmitError();
            }

            subscribe();
        }
    }

    private class LoaderSubscriber extends Subscriber<T> {

        @Override
        public void onNext(T t) {
            mHasLastValue = true;
            mLastValue = t;
            if (mEmitter != null) {
                mEmitter.onNext(t);
            }
        }

        @Override
        public void onError(Throwable throwable) {
            mHasError = true;
            mError = throwable;
            if (mEmitter != null) {
                emmitError();
            }
        }

        @Override
        public void onCompleted() {
            mIsCompleted = true;
            if (mEmitter != null) {
                mEmitter.onCompleted();
            }
        }
    }
}
