package com.elegion.android.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.elegion.android.model.GroupInfo;
import com.elegion.android.repository.RepositoryProvider;
import com.elegion.android.rx.RxDecor;
import com.elegion.android.view.ErrorView;
import com.elegion.android.view.LoadingView;
import com.elegion.android.view.MainView;
import com.google.gson.Gson;

import ru.elegion.rxloadermanager.RxLoaderManager;
import ru.elegion.rxloadermanager.RxUtils;
import timber.log.Timber;

/**
 * @author Nikita Bumakov
 */
public class MainPresenter {

    private static final int E_LEGION_GROUP_ID = 34196694;

    private static final String GROUP_INFO_LOADER = "group_info_loader";

    private static final Gson GSON = new Gson();

    @NonNull
    private final MainView mView;

    @NonNull
    private final ErrorView mErrorView;

    @NonNull
    private final LoadingView mLoadingView;

    @NonNull
    private final RxLoaderManager mRxLoaderManager;

    @NonNull
    private StateHandler mStateHandler = new StateHandler();

    public MainPresenter(@NonNull Context context, @NonNull MainView view, @NonNull LoadingView loadingView, @NonNull ErrorView errorView) {
        mView = view;
        mErrorView = errorView;
        mLoadingView = loadingView;
        mRxLoaderManager = RxLoaderManager.get((Activity) context);
    }

    public void dispatchCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mStateHandler = GSON.fromJson(savedInstanceState.getString(StateHandler.class.getName()), StateHandler.class);
        }

        if (mStateHandler.mIsGroupInfoErrorStub) {
            mView.showErrorStub();
        }

        if (mStateHandler.mIsGroupInfoLoading) {
            showLoading();
        }
    }

    public void saveInstantState(@NonNull Bundle outState) {
        outState.putString(StateHandler.class.getName(), GSON.toJson(mStateHandler));
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
        loadContent(true);
    }

    private void loadContent(boolean refresh) {
        RepositoryProvider.provideGroupsRepository()
                .getGroupInfo(E_LEGION_GROUP_ID)
                .compose(RxUtils.async())
                .doOnSubscribe(this::showLoading)
                .compose(refresh
                        ? mRxLoaderManager.restart(GROUP_INFO_LOADER)
                        : mRxLoaderManager.init(GROUP_INFO_LOADER))
                .subscribe(this::handleResponse, this::handleError, this::onCompleted);
    }

    private void handleResponse(@Nullable GroupInfo groupInfo) {
        Timber.d("onNext");
        if (groupInfo != null) {
            mStateHandler.onGroupInfoLoaded();
            mView.hideErrorStub();
            mView.showInfo(groupInfo);
        }
    }

    private void handleError(@NonNull Throwable throwable) {
        Timber.d("onError");
        mStateHandler.onError();
        hideLoading();
        RxDecor.error(mErrorView).call(throwable);

        RepositoryProvider.provideGroupsRepository()
                .hasGroupInfoLocal(E_LEGION_GROUP_ID)
                .subscribe(hasInfo -> {
                    if (!hasInfo) {
                        mView.showErrorStub();
                        mStateHandler.onErrorStubShown();
                    }
                });
    }

    private void onCompleted() {
        Timber.d("onCompleted");
        hideLoading();
    }

    private void showLoading() {
        mStateHandler.onGroupInfoLoading();
        mLoadingView.showLoadingIndicator();
    }

    private void hideLoading() {
        mStateHandler.onLoadingComplete();
        mLoadingView.hideLoadingIndicator();
    }

    private static class StateHandler {

        private boolean mIsGroupInfoLoading = false;

        private boolean mIsGroupInfoErrorStub = false;

        void onGroupInfoLoaded() {
            mIsGroupInfoErrorStub = false;
        }

        void onGroupInfoLoading() {
            mIsGroupInfoLoading = true;
        }

        void onLoadingComplete() {
            mIsGroupInfoLoading = false;
        }

        void onError() {
            mIsGroupInfoLoading = false;
        }

        void onErrorStubShown() {
            mIsGroupInfoErrorStub = true;
        }
    }
}
