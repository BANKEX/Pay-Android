package ru.elegion.rxloadermanager;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import rx.Observable;

/**
 * @author Nikita Bumakov
 */
public class RxLoader<T> {

    @IdRes
    private int mLoaderId;

    @NonNull
    private Observable<T> mObservable;

    @NonNull
    private RxLoaderObserver<T> mObserver;

    @NonNull
    private RxLifecycleFragment mRxLifecycleFragment;

    public RxLoader(@IdRes int loaderId, @NonNull Observable<T> observable, @NonNull RxLoaderObserver<T> observer, @NonNull RxLifecycleFragment rxLifecycleFragment) {
        mLoaderId = loaderId;
        mObservable = observable;
        mObserver = observer;
        mRxLifecycleFragment = rxLifecycleFragment;
    }

    public void init() {
        RxWorkObserver<T> worker = mRxLifecycleFragment.get(mLoaderId);
        if (worker == null) {
            mRxLifecycleFragment.put(mLoaderId, createWorker(mObservable));
        } else {
            worker.initObserver(mObserver);
        }
    }

    public void restart() {
        RxWorkObserver<T> worker = mRxLifecycleFragment.get(mLoaderId);
        if (worker != null) {
            worker.unsubscribe();
        }
        mRxLifecycleFragment.put(mLoaderId, createWorker(mObservable));
    }

    public RxLoader<T> compose(@NonNull Observable.Transformer<T,T> transformer){
        mObservable = mObservable.compose(transformer);
        return this;
    }

    public RxLoader<T> async(){
        mObservable = mObservable.compose(RxUtil.<T>async());
        return this;
    }

    private RxWorkObserver<T> createWorker(@NonNull Observable<T> observable) {
        RxWorkObserver<T> workObserver = new RxWorkObserver<>(mObserver);
        workObserver.setSubscription(observable.subscribe(workObserver));
        return workObserver;
    }

}
