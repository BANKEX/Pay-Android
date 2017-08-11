package com.elegion.android.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import com.elegion.android.AppDelegate;
import com.elegion.android.data.Repository;
import com.elegion.android.data.model.ErrorBean;
import com.elegion.android.data.remote.response.EmptyListResponse;
import com.elegion.android.ui.base.view.ErrorStubView;
import com.elegion.android.ui.base.view.ErrorView;
import com.elegion.android.ui.base.view.LoadingView;
import com.elegion.android.ui.base.view.NoInternetStubView;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import retrofit2.HttpException;
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
    private static final List<Class<?>> NETWORK_EXCEPTIONS = Arrays.asList(
            UnknownHostException.class,
            SocketTimeoutException.class,
            ConnectException.class
    );

    public static void unsubscribe(@Nullable Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public static boolean isNullOrUnsubscribed(@Nullable Subscription subscription) {
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

    @NonNull
    public static <T> Observable.Transformer<T, T> errorTransformer(@NonNull ErrorView errorView,
                                                                    @NonNull Repository repository,
                                                                    @Nullable NoInternetStubView noInternetStubView) {
        return observable -> observable
                .doOnSubscribe(() -> {
                    if (noInternetStubView != null) {
                        noInternetStubView.hideNoInternetStub();
                    }
                })
                .doOnError(error(errorView, repository));
    }

    @NonNull
    public static Action1<Throwable> error(@NonNull ErrorView errorView, @NonNull Repository repository) {
        return e -> {
            Timber.d(e, "from RxUtils.error");
            handleError(e, errorView, repository);
        };
    }

    private static void handleError(Throwable e, @NonNull ErrorView errorView, @NonNull Repository repository) {
        if (e instanceof HttpException) {
            final HttpException httpException = (HttpException) e;
            if (httpException.code() == 401) {
                errorView.showErrorMessage(httpException.message());
                final Context context = AppDelegate.getAppContext();
                if (context != null) {
                    AuthUtils.logout(repository);
                    AuthUtils.openLogin(context);
                }
            } else {
                try {
                    final String errorBody = httpException.response().errorBody().string();
                    final ErrorBean errorBean = GsonUtils.requestGson().fromJson(errorBody, ErrorBean.class);
                    if (errorBean != null) {
                        errorView.showErrorMessage(errorBean.getMessage());
                    } else {
                        errorView.showErrorMessage(httpException.message());
                    }
                } catch (IOException | IllegalStateException | JsonSyntaxException e1) {
                    errorView.showErrorMessage(httpException.message());
                }
            }
        } else if (NETWORK_EXCEPTIONS.contains(e.getClass())) {
            errorView.showNetworkError();
        } else {
            errorView.showUnexpectedError();
        }
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> emptyStub(@NonNull ErrorStubView view) {
        return observable -> observable
                .doOnSubscribe(view::hideErrorStub)
                .switchIfEmpty(emptyObservable(view));
    }

    private static <T> Observable<T> emptyObservable(@NonNull ErrorStubView view) {
        return Observable.create((Observable.OnSubscribe<T>) Observer::onCompleted).doOnCompleted(view::showErrorStub);
    }

    public static void errorNoAction(Throwable e) {
        Timber.d(e, "errorNoAction");
    }

    public static <T> Observable.Transformer<T, T> emptyTransformer() {
        return tObservable -> tObservable;
    }
}
