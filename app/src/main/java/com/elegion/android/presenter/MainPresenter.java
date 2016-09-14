package com.elegion.android.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.elegion.android.R;
import com.elegion.android.model.GroupInfo;
import com.elegion.android.repository.RepositoryProvider;
import com.elegion.android.rx.RxDecor;
import com.elegion.android.view.ErrorView;
import com.elegion.android.view.LoadingView;
import com.elegion.android.view.MainView;

import ru.elegion.rxloadermanager.RxLoaderManager;
import ru.elegion.rxloadermanager.RxSchedulers;
import ru.elegion.rxloadermanager.RxUtils;

/**
 * @author Nikita Bumakov
 */
public class MainPresenter {

    private static final String ERROR_KEY = "error_key";
    private static final String LOADING_KEY = "loading_key";
    private static final String ERROR_HANDLED_KEY = "is_error_handled_key";

    private static final int E_LEGION_GROUP_ID = 34196694;

    @NonNull
    private final MainView mView;

    @NonNull
    private final ErrorView mErrorView;

    @NonNull
    private final LoadingView mLoadingView;

    @NonNull
    private final RxLoaderManager mRxLoaderManager;

    private boolean mIsLoaded = false;

    private boolean mIsLoading = false;

    private boolean mIsError = false;

    private boolean mIsErrorHandled = false;

    public MainPresenter(@NonNull Context context, @NonNull MainView view, @NonNull LoadingView loadingView, @NonNull ErrorView errorView) {
        mView = view;
        mErrorView = errorView;
        mLoadingView = loadingView;
        mRxLoaderManager = RxLoaderManager.get((Activity) context);
    }

    public void dispatchCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mIsError = savedInstanceState.getBoolean(ERROR_KEY, false);
            mIsLoading = savedInstanceState.getBoolean(LOADING_KEY, false);
            mIsErrorHandled = savedInstanceState.getBoolean(ERROR_HANDLED_KEY, false);
        }

        if (mIsError) {
            mView.showErrorStub();
        }

        if (mIsLoading) {
            showLoading();
        }
    }

    public void saveInstantState(@NonNull Bundle outState) {
        outState.putBoolean(ERROR_KEY, mIsError);
        outState.putBoolean(LOADING_KEY, mIsLoading);
        outState.putBoolean(ERROR_HANDLED_KEY, mIsErrorHandled);
    }

    public void dispatchStart() {
        loadContent(false);
    }

    public void dispatchStop() {
        //do noting
    }

    public void dispatchDestroy() {
        //do noting
    }

    public void refresh() {
        mIsErrorHandled = false;
        loadContent(true);
    }

    private void loadContent(boolean refresh) {
        RepositoryProvider.provideGroupsRepository()
                .getGroupInfo(E_LEGION_GROUP_ID)
                .compose(RxUtils.async())
                .doOnSubscribe(this::showLoading)
                .compose(refresh
                        ? mRxLoaderManager.restart(R.id.group_info_loader)
                        : mRxLoaderManager.init(R.id.group_info_loader))
                .observeOn(RxSchedulers.main())
                .subscribe(this::handleResponse, this::handleError, this::onCompleted);
    }

    private void handleResponse(@Nullable GroupInfo groupInfo) {
        if (groupInfo != null) {
            mIsError = false;
            mIsLoaded = true;
            mView.hideErrorStub();
            mView.showInfo(groupInfo);
        }
    }

    private void handleError(@NonNull Throwable throwable) {
        hideLoading();
        if (!mIsErrorHandled) {
            mIsErrorHandled = true;
            RxDecor.error(mErrorView).call(throwable);
        }
        if (!mIsLoaded) {
            mIsError = true;
            mView.showErrorStub();
        }
    }

    private void onCompleted() {
        hideLoading();
    }

    private void showLoading() {
        mIsLoading = true;
        mLoadingView.showLoadingIndicator();
    }

    private void hideLoading() {
        mIsLoading = false;
        mLoadingView.hideLoadingIndicator();
    }
}
