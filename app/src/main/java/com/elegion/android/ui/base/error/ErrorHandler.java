package com.elegion.android.ui.base.error;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.elegion.android.AppDelegate;
import com.elegion.android.ui.base.view.ErrorView;
import com.elegion.android.ui.base.view.NoInternetStubView;
import com.elegion.android.util.AuthUtils;
import com.elegion.android.util.GsonUtils;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * @author Max Kuznetsov on 15-Jun-17.
 */

public class ErrorHandler {
    public static final int PROFILE_DELETED_CODE = 410;
    public static final int PROFILE_TEMPORARY_BLOCKED = 411;
    public static final int PROFILE_BLOCKED = 412;

    protected static final List<Class<?>> NETWORK_EXCEPTIONS = Arrays.asList(
            UnknownHostException.class,
            SocketTimeoutException.class,
            ConnectException.class
    );

    private ErrorView mErrorView;
    private NoInternetStubView mNoInternetStubView;

    public static ErrorHandler create(@NonNull ErrorView errorView, @Nullable NoInternetStubView noInternetStubView) {
        return new ErrorHandler(errorView, noInternetStubView);
    }

    public ErrorHandler(@NonNull ErrorView errorView, @Nullable NoInternetStubView noInternetStubView) {
        this(errorView);
        mNoInternetStubView = noInternetStubView;
    }

    public ErrorHandler(@NonNull ErrorView errorView) {
        mErrorView = errorView;
    }

    @NonNull
    public final <T> Observable.Transformer<T, T> transformer() {
        return observable -> observable
                .doOnSubscribe(() -> {
                    if (mNoInternetStubView != null) {
                        mNoInternetStubView.hideNoInternetStub();
                    }
                })
                .doOnError(error());
    }

    @NonNull
    public Action1<Throwable> error() {
        return e -> {
            Timber.d(e, "from RxUtils.error");
            handleError(e);
        };
    }

    private void handleError(Throwable e) {
        if (e instanceof HttpException) {
            final HttpException httpException = (HttpException) e;
            int httpCode = httpException.code();
            // TODO: rewrite to handle general unauthorized error
            if (httpCode == PROFILE_DELETED_CODE) {
                mRepository.logout();
                AuthUtils.openLoginActivity(AppDelegate.getAppContext());
            } else if (httpCode == PROFILE_TEMPORARY_BLOCKED) {
                final Context context = AppDelegate.getAppContext();
                context.startActivity(BlockedActivity.makeIntent(context, MainLaunchMode.TEMPORARY_BLOCKED));
            } else if (httpCode == PROFILE_BLOCKED) {
                final Context context = AppDelegate.getAppContext();
                context.startActivity(BlockedActivity.makeIntent(context, MainLaunchMode.BLOCKED));
            } else {
                try {
                    final String errorBody = httpException.response().errorBody().string();
                    final ErrorBean errorBean = GsonUtils.requestGson().fromJson(errorBody, ErrorBean.class);
                    if (errorBean != null) {
                        handleProtocolError(errorBean, httpException);
                    } else {
                        handleNonProtocolError(httpException);
                    }
                } catch (IOException | IllegalStateException | JsonSyntaxException e1) {
                    handleNonProtocolError(httpException);
                }
            }
        } else if (NETWORK_EXCEPTIONS.contains(e.getClass())) {
            if (null == mNoInternetStubView || !mNoInternetStubView.showNoInternetStub()) {
                handleNetworkError(e);
            }
        } else {
            handleUnexpectedError(e);
        }
    }

    protected void handleProtocolError(ErrorBean errorBean, HttpException httpException) {
        mErrorView.showErrorMessage(errorBean != null ? errorBean.getMessage() : httpException.message());
    }

    protected void handleNonProtocolError(HttpException httpException) {
        mErrorView.showErrorMessage(httpException.message());
    }

    protected void handleNetworkError(Throwable e) {
        mErrorView.showNetworkError();
    }

    protected void handleUnexpectedError(Throwable e) {
        mErrorView.showUnexpectedError();
    }
}
