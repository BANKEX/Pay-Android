package com.elegion.android.rx.observer;

import android.support.annotation.NonNull;

import com.elegion.android.view.LoadingView;

import ru.elegion.rxloadermanager.RxLoaderObserver;

/**
 * @author Nikita Bumakov
 */
public class RxLoadingObserver<T> extends RxLoaderObserver<T> {

    @NonNull
    private final LoadingView mLoadingView;

    public RxLoadingObserver(@NonNull LoadingView loadingView) {
        mLoadingView = loadingView;
    }

    @Override
    public void onStarted() {
        super.onStarted();
        mLoadingView.showLoadingIndicator();
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        mLoadingView.hideLoadingIndicator();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        mLoadingView.hideLoadingIndicator();
    }
}
