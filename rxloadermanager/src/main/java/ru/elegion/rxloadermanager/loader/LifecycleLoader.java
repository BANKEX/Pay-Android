package ru.elegion.rxloadermanager.loader;

import android.content.Context;
import android.content.Loader;
import android.support.annotation.NonNull;

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

    private boolean mIsCompleted = false;

    private T mResult;

    public LifecycleLoader(@NonNull Context context) {
        super(context);
        mSubscription = new CompositeSubscription();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
            mSubscription.clear();
            mSubscription = null;
        }
        super.onStopLoading();
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

    public Observable.Transformer<? super T, T> transform(final boolean restart) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                if (restart) {
                    mIsCompleted = false;
                    mResult = null;
                } else {
                    if (mIsCompleted) {
                        return Observable.just(mResult);
                    } else {
                        observable = Observable.just(mResult)
                                .concatWith(observable);
                    }
                }

                return observable
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
            }
        };
    }
}
