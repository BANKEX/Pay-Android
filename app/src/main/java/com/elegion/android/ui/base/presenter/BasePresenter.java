package com.elegion.android.ui.base.presenter;

import rx.subscriptions.CompositeSubscription;

/**
 * @author mikhail barannikov
 */
public class BasePresenter {
    private boolean mIsViewAttached;
    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter() {
        mCompositeSubscription = new CompositeSubscription();
        mIsViewAttached = true;
    }

    public void setViewAttached(boolean viewAttached) {
        mIsViewAttached = viewAttached;
        if (!viewAttached) {
            mCompositeSubscription.clear();
        }
    }

    public boolean isViewAttached() {
        return mIsViewAttached;
    }
}
