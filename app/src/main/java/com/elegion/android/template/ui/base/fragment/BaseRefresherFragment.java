package com.elegion.android.template.ui.base.fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import com.elegion.android.template.R;

import butterknife.BindView;
import timber.log.Timber;

/**
 * @author Mike
 */
public abstract class BaseRefresherFragment extends BaseNoInternetFragment
        implements SwipeRefreshLayout.OnRefreshListener {
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
        Timber.d("MOXY: Refresher showLoadingIndicator()");
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
        Timber.d("MOXY: Refresher hideLoadingIndicator()");
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(() -> {
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }
}
