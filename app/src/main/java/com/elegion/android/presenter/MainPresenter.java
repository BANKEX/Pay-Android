package com.elegion.android.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.elegion.android.R;
import com.elegion.android.repository.RepositoryProvider;
import com.elegion.android.rx.RxDecor;
import com.elegion.android.view.ErrorView;
import com.elegion.android.view.LoadingView;
import com.elegion.android.view.MainView;

import java.util.concurrent.TimeUnit;

import ru.elegion.rxloadermanager.RxLoaderManager;
import ru.elegion.rxloadermanager.RxUtils;
import rx.Observable;
import rx.Subscription;
import timber.log.Timber;

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

    @Nullable
    private Subscription mSubscription;

    @NonNull
    private final Handler mHandler = new Handler();

    @NonNull
    private final Runnable mUnsubscribeRunnable = new Runnable() {
        @Override
        public void run() {
            if (mSubscription != null) {
                mSubscription.unsubscribe();
            }
        }
    };

    public MainPresenter(@NonNull Context context, @NonNull MainView view, @NonNull LoadingView loadingView, @NonNull ErrorView errorView) {
        mView = view;
        mErrorView = errorView;
        mLoadingView = loadingView;
        mRxLoaderManager = RxLoaderManager.get((Activity) context);
    }

    public void dispatchStart() {
        loadContent(false);
        initSubscription();
    }

    public void dispatchStop() {
        mHandler.postDelayed(mUnsubscribeRunnable, TimeUnit.SECONDS.toMillis(30));
    }

    private void initSubscription() {
        mHandler.removeCallbacks(mUnsubscribeRunnable);
        mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                .compose(RxUtils.async())
                .compose(mRxLoaderManager.init(R.id.timer_loader))
                .subscribe(value -> {
                    if (value != null) {
                        mView.showSubscriptionValue(value);
                    }
                }, throwable -> {
                    Timber.e(throwable, throwable.getMessage());
                });
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
