package com.elegion.android.template.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import com.elegion.android.template.AppDelegate;
import com.elegion.android.template.data.Repository;
import com.elegion.android.template.data.model.ErrorBean;
import com.elegion.android.template.data.remote.response.EmptyListResponse;
import com.elegion.android.template.ui.base.view.ErrorStubView;
import com.elegion.android.template.ui.base.view.ErrorView;
import com.elegion.android.template.ui.base.view.LoadingView;
import com.elegion.android.template.ui.base.view.NoInternetStubView;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
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

    public static void dispose(@Nullable CompositeDisposable subscription) {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }
    }

    public static boolean isNullOrUnsubscribed(@Nullable Disposable disposable) {
        return disposable == null || disposable.isDisposed();
    }

    @NonNull
    public static <T> Flowable<T> async(Flowable<T> flowable) {
        return flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    public static <T> FlowableTransformer<T, T> loading(@NonNull LoadingView view) {
        return observable -> observable
                .doOnSubscribe(d -> {
                    view.showLoadingIndicator();
                    Timber.d("LOADING: doOnSubscribe");
                })
                .doFinally(() -> {
                    Timber.d("LOADING: doOnTerminate");
                    view.hideLoadingIndicator();
                });
    }

    @NonNull
    public static <T extends EmptyListResponse> FlowableTransformer<T, T> emptyErrorStub(@NonNull ErrorStubView view) {
        return observable -> observable
                .doOnSubscribe(view::hideErrorStub)
                .doOnNext(response -> {
                    if (response.isEmpty()) {
                        view.showErrorStub();
                    }
                });
    }

    @NonNull
    public static <F extends EmptyListResponse, S extends EmptyListResponse,
            T extends Pair<F, S>> FlowableTransformer<T, T> emptyPairErrorStub(@NonNull ErrorStubView view) {
        return observable -> observable
                .doOnSubscribe(view::hideErrorStub)
                .doOnNext(pair -> {
                    if (pair.first != null && pair.first.isEmpty() && pair.second != null && pair.second.isEmpty()) {
                        view.showErrorStub();
                    }
                });
    }

    @NonNull
    public static <T> FlowableTransformer<T, T> errorTransformer(@NonNull ErrorView errorView,
                                                               @NonNull Repository repository,
                                                               @Nullable NoInternetStubView noInternetStubView) {
        return observable -> observable
                .doOnNext(v -> {
                    if (noInternetStubView != null) {
                        noInternetStubView.hideNoInternetStub();
                    }
                })
                .doOnError(error(errorView, repository));
    }

    @NonNull
    public static Consumer<Throwable> error(@NonNull ErrorView errorView, @NonNull Repository repository) {
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

    public static void errorLogE(Throwable e) {
        Timber.d(e, "errorLogE");
    }

    public static <T> FlowableTransformer<T, T> emptyTransformer() {
        return tObservable -> tObservable;
    }
}
