package com.elegion.android.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.R;
import com.elegion.android.repository.RepositoryProvider;
import com.elegion.android.rx.RxDecor;
import com.elegion.android.view.ErrorView;
import com.elegion.android.view.LoadingView;
import com.elegion.android.view.MainView;

import ru.elegion.rxloadermanager.RxLoaderManager;
import ru.elegion.rxloadermanager.RxUtils;

/**
 * @author Nikita Bumakov
 */
public class MainPresenter {

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

    private boolean mIsErrorHandled = false;

    public MainPresenter(@NonNull Context context, @NonNull MainView view, @NonNull LoadingView loadingView, @NonNull ErrorView errorView) {
        mView = view;
        mErrorView = errorView;
        mLoadingView = loadingView;
        mRxLoaderManager = RxLoaderManager.get((Activity) context);
    }

    public void dispatchStart() {
        loadContent(false);
    }

    public void dispatchStop() {
    }

    private void loadContent(boolean refresh) {
        RepositoryProvider.provideGroupsRepository()
                .getGroupInfo(E_LEGION_GROUP_ID)
                .compose(RxUtils.async())
                .compose(refresh
                        ? mRxLoaderManager.restart(R.id.group_info_loader)
                        : mRxLoaderManager.init(R.id.group_info_loader))
                .compose(RxDecor.loading(mLoadingView))
                .subscribe(
                        groupInfo -> {
                            if (groupInfo != null) {
                                mIsLoaded = true;
                                mView.hideErrorStub();
                                mView.showInfo(groupInfo);
                            }
                        },
                        throwable -> {
                            if (!mIsLoaded) {
                                mView.showErrorStub();
                                if (!mIsErrorHandled) {
                                    RxDecor.error(mErrorView).call(throwable);
                                }
                            }
                        }
                );
    }

    public void refresh() {
        loadContent(true);
    }

    public void dispatchDestroy() {
        //do noting
    }
}
