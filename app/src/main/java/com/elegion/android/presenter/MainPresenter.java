package com.elegion.android.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.elegion.android.R;
import com.elegion.android.model.GroupInfo;
import com.elegion.android.repository.RepositoryProvider;
import com.elegion.android.rx.observer.RxCompositeObserver;
import com.elegion.android.rx.observer.RxErrorObserver;
import com.elegion.android.rx.observer.RxLoadingObserver;
import com.elegion.android.view.ErrorView;
import com.elegion.android.view.LoadingView;
import com.elegion.android.view.MainView;

import java.util.concurrent.TimeUnit;

import ru.elegion.rxloadermanager.RxLoaderManager;
import rx.Observable;

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

    public MainPresenter(@NonNull MainView view, @NonNull LoadingView loadingView, @NonNull ErrorView errorView, @NonNull RxLoaderManager loaderManager) {
        mView = view;
        mRxLoaderManager = loaderManager;
        mGroupInfoObserver = new GroupInfoObserver(loadingView, errorView);
    }

    public void dispatchStart() {
        loadContent();
    }

    public void dispatchStop() {
    }

    private void loadContent() {
        Observable<GroupInfo> observable = RepositoryProvider.provideGroupsRepository()
                .getGroupInfo(E_LEGION_GROUP_ID)
                .delay(3, TimeUnit.SECONDS);
        mRxLoaderManager.create(R.id.group_info_loader, observable, mGroupInfoObserver)
                .async()
                .init();
    }

    private class GroupInfoObserver extends RxCompositeObserver<GroupInfo> {

        public GroupInfoObserver(@NonNull LoadingView loadingView, @NonNull ErrorView errorView) {
            super(new RxLoadingObserver<>(loadingView),
                    new RxErrorObserver<>(errorView));
        }

        @Override
        public void onNext(@Nullable GroupInfo info) {
            mView.showInfo(info);
        }
    }
}
