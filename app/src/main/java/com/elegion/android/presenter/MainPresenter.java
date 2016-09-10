package com.elegion.android.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.elegion.android.R;
import com.elegion.android.model.GroupInfo;
import com.elegion.android.repository.RepositoryProvider;
import com.elegion.android.view.MainView;

import ru.elegion.rxloadermanager.RxLoaderManager;
import ru.elegion.rxloadermanager.RxLoaderObserver;
import rx.Observable;
import timber.log.Timber;

/**
 * @author Nikita Bumakov
 */
public class MainPresenter {

    private static final int E_LEGION_GROUP_ID = 34196694;

    @NonNull
    private final Context mContext;

    @NonNull
    private final MainView mView;

    @NonNull
    private final GroupInfoObserver mGroupInfoObserver = new GroupInfoObserver();

    @NonNull
    private final RxLoaderManager mRxLoaderManager;

    public MainPresenter(@NonNull Context context, @NonNull MainView view, @NonNull RxLoaderManager loaderManager) {
        mContext = context;
        mView = view;
        mRxLoaderManager = loaderManager;
    }

    public void dispatchStart() {
        loadContent();
    }

    public void dispatchStop() {
    }

    private void loadContent() {
        Observable<GroupInfo> observable = RepositoryProvider.provideGroupsRepository().getGroupInfo(E_LEGION_GROUP_ID);
        mRxLoaderManager.create(R.id.group_info_loader, observable, mGroupInfoObserver);

    }

    private class GroupInfoObserver extends RxLoaderObserver<GroupInfo> {
        @Override
        public void onStarted() {
            Timber.d("onStarted");
        }

        @Override
        public void onNext(GroupInfo result) {
            mView.showMainText("" + result);
        }

        @Override
        public void onError(Throwable error) {
            Timber.e(error, error.getMessage());
        }
    }
}
