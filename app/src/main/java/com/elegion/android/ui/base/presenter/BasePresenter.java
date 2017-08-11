package com.elegion.android.ui.base.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.elegion.android.util.RxUtils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author mikhail.barannikov on 11.08.2017
 */
public class BasePresenter<View extends MvpView> extends MvpPresenter<View> {
    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter() {
        mCompositeSubscription = new CompositeSubscription();
    }

    public void removeSubscription(Subscription s) {
        mCompositeSubscription.remove(s);
    }

    public void addSubscription(Subscription s) {
        mCompositeSubscription.add(s);
    }

    public void clearSubscribtions() {
        mCompositeSubscription.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxUtils.unsubscribe(mCompositeSubscription);
    }
}
