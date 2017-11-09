package com.elegion.android.template.ui.base.presenter;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;
import com.elegion.android.template.util.RxUtils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author mikhail.barannikov on 11.08.2017
 */
public class BasePresenter<V extends MvpView> extends MvpPresenter<V> {
    protected CompositeDisposable mCompositeDisposable;

    public BasePresenter() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public void removeDisposable(Disposable d) {
        if (d != null) {
            mCompositeDisposable.remove(d);
        }
    }

    public void addDisposable(Disposable d) {
        if (d != null) {
            mCompositeDisposable.add(d);
        }
    }

    public void clearDisposables() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxUtils.dispose(mCompositeDisposable);
    }
}
