package com.elegion.android.ui.base.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.pchela.android.R;
import com.pchela.android.ui.base.presenter.BasePresenter;
import com.pchela.android.ui.base.view.NoInternetStubView;
import com.pchela.android.ui.widget.EmptyView;
import com.pchela.android.util.ViewUtils;

/**
 * @author mikhail barannikov
 */
public abstract class AbstractNoInternetFragment<T extends BasePresenter> extends BindViewFragment<T> implements NoInternetStubView {

    protected EmptyView mNoInternetView;

    private View.OnClickListener mTryAgainListener = v -> tryAgain();

    @Override
    @CallSuper
    protected void bindViews(View view) {
        mNoInternetView = ViewUtils.findView(view, R.id.no_internet_view);
    }

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void tryAgain();

    protected abstract View[] getHideViews();

    @Override
    public void onResume() {
        super.onResume();
        mNoInternetView.setButtonClickListener(mTryAgainListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mNoInternetView.setButtonClickListener(null);
    }

    @Override
    public void hideNoInternetStub() {
        ViewUtils.setVisibility(View.GONE, mNoInternetView);
        ViewUtils.setVisibility(View.VISIBLE, getHideViews());
    }

    @Override
    public boolean showNoInternetStub() {
        ViewUtils.setVisibility(View.VISIBLE, mNoInternetView);
        ViewUtils.setVisibility(View.GONE, getHideViews());
        return true;
    }
}