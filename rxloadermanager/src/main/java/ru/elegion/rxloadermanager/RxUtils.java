package ru.elegion.rxloadermanager;

import android.support.annotation.NonNull;

import rx.Observable;

/**
 * @author Nikita Bumakov
 */
public final class RxUtils {

    private RxUtils() {
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> async() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(RxSchedulers.io())
                        .observeOn(RxSchedulers.main());
            }
        };
    }
}
