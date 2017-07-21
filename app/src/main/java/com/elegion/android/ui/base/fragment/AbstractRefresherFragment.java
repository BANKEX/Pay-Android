package com.elegion.android.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.android.ui.base.presenter.BasePresenter;
import com.elegion.android.ui.base.view.LoadingView;
import com.elegion.android.util.ViewUtils;

/**
 * @author mikhail.funikov@e-legion.com on 26/06/2017.
 */

public abstract class AbstractRefresherFragment<T extends BasePresenter>
        extends AbstractNoInternetFragment<T> implements LoadingView, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    protected void bindViews(View view) {
        super.bindViews(view);
        mRefreshLayout = ViewUtils.findView(view, R.id.refresher);
    }

    @Override
    public void showLoadingIndicator() {
        if (mRefreshLayout != null) {
            mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(true));
        }
    }

    @Override
    public void hideLoadingIndicator() {
        if (mRefreshLayout != null) {
            mRefreshLayout.post(() -> mRefreshLayout.setRefreshing(false));
        }
    }

    public SwipeRefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }
}
