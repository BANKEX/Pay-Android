package com.elegion.android.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.pchela.android.ui.base.presenter.BasePresenter;

/**
 * @author Max Kuznetsov on 16-Jun-17.
 */

public abstract class AttachableFragment<T extends BasePresenter> extends Fragment {

    private T mPresenter;

    protected T getPresenter() {
        return mPresenter;
    }

    protected abstract T createPresenter(@Nullable Bundle savedInstanceState);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().setViewAttached(true);
        }
    }

    @Override
    public void onDestroyView() {
        if (getPresenter() != null) {
            getPresenter().setViewAttached(false);
        }
        super.onDestroyView();
    }

}
