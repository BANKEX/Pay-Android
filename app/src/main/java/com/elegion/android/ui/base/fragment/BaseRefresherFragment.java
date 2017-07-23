package com.elegion.android.ui.base.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.android.R;
import com.elegion.android.ui.base.view.LoadingView;

import butterknife.BindView;

/**
 * @author Mike
 */
public abstract class BaseRefresherFragment extends BaseNoInternetFragment
        implements LoadingView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.refresher)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onResume() {
        super.onResume();
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onPause() {
        mSwipeRefreshLayout.setOnRefreshListener(null);
        super.onPause();
    }

    @Override
    public void showLoadingIndicator() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(() -> {
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void hideLoadingIndicator() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(() -> {
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }
}
