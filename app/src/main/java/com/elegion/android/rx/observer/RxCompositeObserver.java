package com.elegion.android.rx.observer;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.elegion.rxloadermanager.RxLoaderObserver;

/**
 * @author Nikita Bumakov
 */
public class RxCompositeObserver<T> extends RxLoaderObserver<T> {

    @NonNull
    private final List<RxLoaderObserver<T>> mObserverList = new ArrayList<>();

    @SafeVarargs
    public RxCompositeObserver(@NonNull RxLoaderObserver<T>... rxLoaderObservers) {
        Collections.addAll(mObserverList, rxLoaderObservers);
    }

    @Override
    public void onStarted() {
        super.onStarted();
        for(RxLoaderObserver observer: mObserverList){
            observer.onStarted();
        }
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        for(RxLoaderObserver observer: mObserverList){
            observer.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        for(RxLoaderObserver observer: mObserverList){
            observer.onError(e);
        }
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
        for(RxLoaderObserver<T> observer: mObserverList){
            observer.onNext(t);
        }
    }
}
