package com.elegion.android.ui.features;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.elegion.android.data.Repository;
import com.elegion.android.data.model.Feature;
import com.elegion.android.util.AuthUtils;
import com.elegion.android.util.RxUtils;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author mikhail.barannikov on 24.07.2017
 */
@InjectViewState
public class FeaturesPresenter extends MvpPresenter<FeaturesView> {
    private static final int PAGE_COUNT = 20;

    private int mOffset = 0;
    private Repository mRepository;
    private CompositeSubscription mSubscription;
    private Subscription mLoadFeaturesSubscription;
    private boolean mIsLastPage;

    public FeaturesPresenter(Repository repository) {
        mRepository = repository;
        mSubscription = new CompositeSubscription();
    }

    @Override
    protected void onFirstViewAttach() {
        loadFeatures(true);
    }

    public void loadFeatures(boolean refresh) {
        if (refresh) {
            mOffset = 0;
            mIsLastPage = false;
        }

        if (!mIsLastPage && RxUtils.isNullOrUnsubscribed(mLoadFeaturesSubscription)) {
            mLoadFeaturesSubscription = mRepository.getFeatures(mOffset, PAGE_COUNT)
                    .compose(RxUtils::async)
                    .compose(RxUtils.loading(getViewState()))
                    .subscribe(this::handleFeaturesResponse, RxUtils::errorNoAction);
        }
    }

    private void handleFeaturesResponse(List<Feature> features) {
        final boolean clear = mOffset == 0;
        if (clear) {
            getViewState().clearFeatures();
        }
        getViewState().showFeatures(features, clear);
        mOffset = mOffset + features.size();
        mIsLastPage = mOffset > 50;
    }

    public void logout() {
        AuthUtils.logout(mRepository);
    }
}
