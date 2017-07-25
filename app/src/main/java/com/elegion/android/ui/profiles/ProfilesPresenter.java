package com.elegion.android.ui.profiles;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.elegion.android.data.Repository;
import com.elegion.android.data.model.UserProfile;
import com.elegion.android.util.RxUtils;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author mikhail.barannikov on 24.07.2017
 */
@InjectViewState
public class ProfilesPresenter extends MvpPresenter<ProfilesView> {
    private static final int PAGE_COUNT = 20;

    private int mOffset = 0;
    private Repository mRepository;
    private CompositeSubscription mSubscription;
    private Subscription mLoadProfilesSubscription;
    private boolean mIsLastPage;

    public ProfilesPresenter(Repository repository) {
        mRepository = repository;
        mSubscription = new CompositeSubscription();
    }

    @Override
    protected void onFirstViewAttach() {
        loadProfiles(true);
    }

    public void loadProfiles(boolean refresh) {
        if (refresh) {
            mOffset = 0;
            mIsLastPage = false;
        }

        if (!mIsLastPage && RxUtils.isNullOrUnsubscribed(mLoadProfilesSubscription)) {
            mRepository.getProfiles(mOffset, PAGE_COUNT)
                    .compose(RxUtils::async)
//                    .compose(RxUtils.loading(mLoadingView))
                    .subscribe(this::handleProfilesResponse, RxUtils::errorNoAction);
        }
    }

    private void handleProfilesResponse(List<UserProfile> userProfiles) {
        getViewState().showProfiles(userProfiles, mOffset == 0);
        mOffset = mOffset + userProfiles.size();
        mIsLastPage = mOffset > 50;
    }
}
