package com.elegion.android.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;

import com.elegion.android.R;
import com.elegion.android.model.GroupInfo;
import com.elegion.android.repository.RepositoryProvider;
import com.elegion.android.rx.RxDecor;
import com.elegion.android.rx.RxError;
import com.elegion.android.rx.observer.RxCompositeObserver;
import com.elegion.android.rx.observer.RxErrorObserver;
import com.elegion.android.rx.observer.RxLoadingObserver;
import com.elegion.android.view.ErrorView;
import com.elegion.android.view.LoadingView;
import com.elegion.android.view.MainView;

import java.util.concurrent.TimeUnit;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.arturvasilov.rxloader.RxSchedulers;
import ru.elegion.rxloadermanager.RxLoader;
import ru.elegion.rxloadermanager.RxLoaderManager;
import ru.elegion.rxloadermanager.RxLoaderObserver;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * @author Nikita Bumakov
 */
public class MainPresenter {

    private static final int E_LEGION_GROUP_ID = 34196694;

    @NonNull
    private final MainView mView;

    @NonNull
    private final GroupInfoObserver mGroupInfoObserver;

    @NonNull
    private final RxLoaderManager mRxLoaderManager;

    private boolean mIsLoaded;

    public MainPresenter(Context context, @NonNull MainView view, @NonNull LoadingView loadingView, @NonNull ErrorView errorView, @NonNull RxLoaderManager loaderManager, @NonNull LoaderManager supportLoaderManager) {
        mView = view;
        mRxLoaderManager = loaderManager;
        mGroupInfoObserver = new GroupInfoObserver(loadingView, errorView);
    }

    public void dispatchStart() {
        loadContent(false);
    }

    public void dispatchStop() {
    }

    private void loadContent(boolean refresh) {

        Observable<GroupInfo> observable = RepositoryProvider.provideGroupsRepository()
                .getGroupInfo(E_LEGION_GROUP_ID);

        RxLoader<GroupInfo> loader = mRxLoaderManager.create(R.id.group_info_loader, observable, mGroupInfoObserver)
                .async();

        if (refresh) {
            loader.restart();
        } else {
            loader.init();
        }
    }

    public void refresh() {
        loadContent(true);
    }

    public void dispatchDestroy() {
        //do noting
    }

    private class GroupInfoObserver extends RxCompositeObserver<GroupInfo> {

        public GroupInfoObserver(@NonNull LoadingView loadingView, @NonNull ErrorView errorView) {
            super(new RxLoadingObserver<>(loadingView),
                    new RxErrorObserver<>(errorView));
        }

        @Override
        public void onNext(@Nullable GroupInfo info) {
            if (info != null) {
                mIsLoaded = true;
                mView.hideEmptyStub();
                mView.showInfo(info);
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            if (!mIsLoaded) {
                mView.showEmptyStub();
            }
        }
    }
}
