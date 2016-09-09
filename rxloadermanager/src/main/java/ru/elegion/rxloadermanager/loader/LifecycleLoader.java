package ru.elegion.rxloadermanager.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Nikita Bumakov
 */
class LifecycleLoader<T> extends Loader<Void> {

    private CompositeSubscription mSubscription;

    private Observable<T> mCachedObservable;

    private boolean mIsCompleted = false;

    private T mResult;

    private boolean mIsLoading;

    public LifecycleLoader(@NonNull Context context) {
        super(context);
        mSubscription = new CompositeSubscription();
    }

    @Override
    protected void onStartLoading() {
        mIsLoading = true;
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        mIsLoading = false;
        super.onStopLoading();
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
    }

    @Override
    protected void onReset() {
        super.onReset();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
            mSubscription.clear();
            mCachedObservable = null;
        }
    }

    public Observable.Operator<T, T> lifecycle() {
        return new Observable.Operator<T, T>() {
            @Override
            public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
                mSubscription.add(subscriber);
                return subscriber;
            }
        };
    }

    public Observable.Transformer<T, T> transform(final boolean restart) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                //TODO: refactor
                if (restart) {
                    mIsCompleted = false;
                    mResult = null;
                    mCachedObservable = null;
                } else {
                    if (mIsCompleted) {
                        return Observable.just(mResult);
                    } else if (!isReset() && mCachedObservable != null) {
                        observable = Observable.just(mResult)
                                .concatWith(mCachedObservable);
                    } else {
                        mCachedObservable = observable;
                        observable = Observable.just(mResult)
                                .concatWith(observable);
                    }
                }

                observable = observable
                        .doOnCompleted(new Action0() {
                            @Override
                            public void call() {
                                mIsCompleted = true;
                            }
                        })
                        .doOnNext(new Action1<T>() {
                            @Override
                            public void call(T t) {
                                mResult = t;
                            }
                        });

                mCachedObservable = observable;

                return mCachedObservable;
            }
        };
    }
}
