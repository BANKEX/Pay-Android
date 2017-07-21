package com.elegion.android.ui.base.activity;

import android.os.Bundle;

import com.pchela.android.ui.base.presenter.BasePresenter;

/**
 * @author Max Kuznetsov on 16-Jun-17.
 */

public abstract class AttachableActivity<T extends BasePresenter> extends BaseActivity {

    protected T mPresenter;

    protected T getPresenter() {
        return mPresenter;
    }

    protected abstract T createPresenter(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter(savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().setViewAttached(true);
        }
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().setViewAttached(false);
        }
        super.onDestroy();
    }
}
