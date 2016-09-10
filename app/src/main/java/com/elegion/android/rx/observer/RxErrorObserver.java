package com.elegion.android.rx.observer;

import android.support.annotation.NonNull;

import com.elegion.android.rx.RxDecor;
import com.elegion.android.view.ErrorView;

import ru.elegion.rxloadermanager.RxLoaderObserver;

/**
 * @author Nikita Bumakov
 */
public class RxErrorObserver<T> extends RxLoaderObserver<T> {

    @NonNull
    private final ErrorView mErrorView;

    public RxErrorObserver(@NonNull ErrorView errorView) {
        mErrorView = errorView;
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        RxDecor.error(mErrorView).call(e);
    }
}
