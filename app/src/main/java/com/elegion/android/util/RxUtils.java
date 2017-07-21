package com.elegion.android.util;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.elegion.android.data.remote.response.EmptyListResponse;
import com.elegion.android.ui.base.view.ErrorStubView;
import com.elegion.android.ui.base.view.LoadingView;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author mikhail barannikov
 */

public class RxUtils {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public static <T> Observable.Transformer<T, T> emptyTransformer() {
        return tObservable -> tObservable;
    }

    public static boolean isNullOrUnsubscribed(Subscription subscription) {
        return subscription == null || subscription.isUnsubscribed();
    }

    @NonNull
    public static <T> Observable<T> async(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> loading(@NonNull LoadingView view) {
        return observable -> observable
                .doOnSubscribe(() -> {
                    view.showLoadingIndicator();
                    Timber.d("LOADING: doOnSubscribe");
                })
                .doOnTerminate(() -> {
                    Timber.d("LOADING: doOnTerminate");
                    view.hideLoadingIndicator();
                })
                .doOnUnsubscribe(() -> {
                    Timber.d("LOADING: doOnUnsubscribe");
                    view.hideLoadingIndicator();
                });
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> emptyStub(@NonNull ErrorStubView view) {
        return observable -> observable
                .doOnSubscribe(view::hideErrorStub)
                .switchIfEmpty(emptyObservable(view));
    }

    @NonNull
    public static <T extends EmptyListResponse> Observable.Transformer<T, T> emptyErrorStub(@NonNull ErrorStubView view) {
        return observable -> observable
                .doOnSubscribe(view::hideErrorStub)
                .doOnNext(new Action1<EmptyListResponse>() {
                    @Override
                    public void call(EmptyListResponse response) {
                        if (response.isEmpty()) {
                            view.showErrorStub();
                        }
                    }
                });
    }

    @NonNull
    public static <F extends EmptyListResponse, S extends EmptyListResponse,
            T extends Pair<F, S>> Observable.Transformer<T, T> emptyPairErrorStub(@NonNull ErrorStubView view) {
        return observable -> observable
                .doOnSubscribe(view::hideErrorStub)
                .doOnNext(new Action1<Pair<F, S>>() {
                    @Override
                    public void call(Pair<F, S> pair) {
                        if (pair.first.isEmpty() && pair.second.isEmpty()) {
                            view.showErrorStub();
                        }
                    }
                });
    }

    private static <T> Observable<T> emptyObservable(@NonNull ErrorStubView view) {
        return Observable.create((Observable.OnSubscribe<T>) Observer::onCompleted).doOnCompleted(view::showErrorStub);
    }

    public static void errorNoAction(Throwable e) {
        Timber.d(e, "errorNoAction");
    }
}
