package ru.elegion.rxloadermanager.rxartut;

import android.content.Context;
import android.content.Loader;
import android.support.annotation.NonNull;

import rx.Observable;
import rx.functions.Action1;

/**
 * @author Artur Vasilov
 */
class RxResultLoader<T> extends Loader<RxResult<T>> {

    private final Observable<T> mObservable;

    private boolean mOnNextCalled;

    private T mResult;

    public RxResultLoader(@NonNull Context context, @NonNull Observable<T> observable) {
        super(context);
        mObservable = observable;
    }

    @Override
    public void deliverResult(RxResult<T> data) {
        if (isReset()) {
            return;
        }
        if (data != null) {
            mResult = data.getResult();
        }
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged() || mResult == null) {
            forceLoad();
        }else {
            super.deliverResult(RxResult.onNext(mResult));
        }
    }

    @Override
    protected void onForceLoad() {
        mObservable.subscribe(new Action1<T>() {
            @Override
            public void call(T t) {
                mOnNextCalled = true;
                deliverResult(RxResult.onNext(t));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (!mOnNextCalled) {
                    deliverResult(null);
                }
            }
        });
    }

}
