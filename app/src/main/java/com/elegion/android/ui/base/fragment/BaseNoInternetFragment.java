package com.elegion.android.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.elegion.android.R;
import com.elegion.android.ui.base.view.NoInternetStubView;
import com.elegion.android.ui.widget.EmptyView;
import com.elegion.android.util.ViewUtils;

import butterknife.BindView;

/**
 * @author Mike
 */
public abstract class BaseNoInternetFragment extends BaseFragment implements NoInternetStubView {
    @BindView(R.id.no_internet_view)
    protected EmptyView mNoInternetView;

    // WARNING: retrolambda works incorrectly here, use anonymous class
    private View.OnClickListener mTryAgainListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tryAgain();
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNoInternetView.bindEmptyView(R.drawable.ic_no_network, R.string.no_internet, R.string.try_again, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mNoInternetView.setButtonClickListener(mTryAgainListener);
    }

    @Override
    public void showNetworkError() {
        if (shouldShowNoInternetStubView()) {
            showNoInternetStub();
        } else {
            super.showNetworkError();
        }
    }

    protected boolean shouldShowNoInternetStubView() {
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        mNoInternetView.setButtonClickListener(null);
    }

    @Override
    public void hideNoInternetStub() {
        ViewUtils.setVisibility(View.GONE, getNoInternetViews());
        ViewUtils.setVisibility(View.VISIBLE, getViews());
    }

    @Override
    public void showNoInternetStub() {
        ViewUtils.setVisibility(View.INVISIBLE, getViews());
        ViewUtils.setVisibility(View.VISIBLE, getNoInternetViews());
    }

    protected View[] getNoInternetViews() {
        return new View[]{mNoInternetView};
    }

    protected abstract View[] getViews();

    public abstract void tryAgain();
}
