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

    @Nullable
    private T mLastValue;

    private boolean mIsErrorReported = false;

    @Nullable
    private Throwable mError;

    private boolean mIsCompleted = false;

    public RxLoader(@NonNull Observable<T> observable) {
        mParentObservable = observable;
    }

    @NonNull
    Observable<T> getChildObservable() {
        if (mChildObservable == null) {
            mChildObservable = Observable.fromEmitter(new EmitterAction(), AsyncEmitter.BackpressureMode.LATEST);
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
        mLastValue = null;
        mError = null;
        mIsCompleted = false;
        mIsErrorReported = false;
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

            if (mLastValue != null) {
                mEmitter.onNext(mLastValue);
            }
            if (mIsCompleted) {
                mEmitter.onCompleted();
            } else if (mError != null && !mIsErrorReported) {
                mEmitter.onError(mError);
                mIsErrorReported = true;
            }

            subscribe();
        }
    }

    private class LoaderSubscriber extends Subscriber<T> {

        @Override
        public void onNext(T t) {
            mLastValue = t;
            if (mEmitter != null) {
                mEmitter.onNext(t);
            }
        }

        @Override
        public void onError(Throwable throwable) {
            mError = throwable;
            if (mEmitter != null) {
                mEmitter.onError(throwable);
                mIsErrorReported = true;
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
