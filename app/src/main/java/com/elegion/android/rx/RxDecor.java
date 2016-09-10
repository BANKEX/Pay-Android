package com.elegion.android.rx;

import android.support.annotation.NonNull;

import com.elegion.android.view.EmptyView;
import com.elegion.android.view.ErrorView;
import com.elegion.android.view.LoadingView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * @author Daniel Serdyukov
 */
public final class RxDecor {

    private static final List<Class<?>> NETWORK_EXCEPTIONS = Arrays.asList(
            UnknownHostException.class,
            SocketTimeoutException.class,
            ConnectException.class
    );

    private RxDecor() {
        //Unnecessary constructor
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> loading(@NonNull LoadingView view) {
        return observable -> observable
                .doOnSubscribe(() -> {
                    view.showLoadingIndicator();
                    Timber.tag("LOADING").d("doOnSubscribe");
                })
                .doOnTerminate(() -> {
                    Timber.tag("LOADING").d("doOnTerminate");
                    view.hideLoadingIndicator();
                })
                .doOnUnsubscribe(() -> {
                    Timber.tag("LOADING").d("doOnUnsubscribe");
                    view.hideLoadingIndicator();
                });
    }

    @NonNull
    public static Action1<Throwable> error(@NonNull ErrorView view) {
        return e -> {
            Timber.d(e, "from RxDecor.error");
            if (e instanceof HttpException) {
                view.showErrorMessage(((HttpException) e).message());
            } else if (NETWORK_EXCEPTIONS.contains(e.getClass())) {
                view.showNetworkError();
            } else {
                view.showUnexpectedError();
            }
        };
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> emptyStub(@NonNull EmptyView view) {
        return observable -> observable
                .doOnSubscribe(view::hideEmptyStub)
                .switchIfEmpty(emptyObservable(view));
    }

    private static <T> Observable<T> emptyObservable(@NonNull EmptyView view) {
        return Observable.create((Observable.OnSubscribe<T>) Observer::onCompleted).doOnCompleted(view::showEmptyStub);
    }

}
