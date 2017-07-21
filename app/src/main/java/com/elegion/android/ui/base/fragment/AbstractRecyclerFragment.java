package com.elegion.android.ui.base.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pchela.android.R;
import com.pchela.android.ui.base.presenter.BasePresenter;
import com.pchela.android.ui.base.view.LoadingView;
import com.pchela.android.ui.base.view.NoInternetStubView;
import com.pchela.android.ui.widget.EmptyView;
import com.pchela.android.ui.widget.HeaderEmptyView;
import com.pchela.android.util.ViewUtils;

/**
 * @author azret.magometov on 23-Jan-17.
 */
public abstract class AbstractRecyclerFragment<T extends BasePresenter> extends BindViewFragment<T>
        implements SwipeRefreshLayout.OnRefreshListener, LoadingView, NoInternetStubView {

    protected RecyclerView mRecyclerView;
    protected EmptyView mEmptyView;
    protected EmptyView mNoInternetView;
    protected HeaderEmptyView mHeaderEmptyView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    private View.OnClickListener mTryAgainListener = v -> tryAgain();

    @Override
    @CallSuper
    protected void bindViews(View view) {
        mRecyclerView = ViewUtils.findView(view, R.id.recycler);
        mEmptyView = ViewUtils.findView(view, R.id.empty_view);
        mHeaderEmptyView = ViewUtils.findView(view, R.id.header_empty_view);
        mNoInternetView = ViewUtils.findView(view, R.id.no_internet_view);
        mSwipeRefreshLayout = ViewUtils.findView(view, R.id.refresher);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
        initRecycler();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNoInternetView.bindEmptyView(R.drawable.ic_launcher, R.string.no_internet, R.string.try_again, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mNoInternetView.setButtonClickListener(mTryAgainListener);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onPause() {
        mNoInternetView.setButtonClickListener(null);
        mSwipeRefreshLayout.setOnRefreshListener(null);
        super.onPause();
    }

    protected int getLayout() {
        return R.layout.fr_recycler;
    }

    protected void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(getCertainAdapter());
    }

    protected void addItemDecorator(Drawable drawable) {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(drawable);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    protected abstract RecyclerView.Adapter getCertainAdapter();

    protected void bindEmptyView(@DrawableRes int iconRes, @StringRes int msgTextRes,
                                 @StringRes int buttonTextRes, View.OnClickListener listener) {
        mEmptyView.bindEmptyView(iconRes, msgTextRes, buttonTextRes, listener);
    }

    protected void bindEmptyView(@DrawableRes int iconRes, @StringRes int msgTextRes) {
        mEmptyView.bindEmptyView(iconRes, msgTextRes);
    }

    public void bindEmptyView(@StringRes int text) {
        mEmptyView.bindEmptyView(text);
    }

    public void bindHeaderEmptyView(@StringRes int title) {
        mHeaderEmptyView.bindEmptyView(title);
    }

    public void bindHeaderEmptyView(@StringRes int title, @StringRes int description) {
        mHeaderEmptyView.bindEmptyView(title, description);
    }

    public void showHeaderEmptyView() {
        ViewUtils.setVisibility(View.VISIBLE, mHeaderEmptyView);

        ViewUtils.setVisibility(View.GONE, mRecyclerView);
        ViewUtils.setVisibility(View.GONE, mNoInternetView);
        ViewUtils.setVisibility(View.GONE, mEmptyView);
    }

    public void showEmptyView() {
        ViewUtils.setVisibility(View.VISIBLE, mEmptyView);

        ViewUtils.setVisibility(View.GONE, mRecyclerView);
        ViewUtils.setVisibility(View.GONE, mNoInternetView);
        ViewUtils.setVisibility(View.GONE, mHeaderEmptyView);
    }

    public void showItems() {
        ViewUtils.setVisibility(View.VISIBLE, mRecyclerView);

        ViewUtils.setVisibility(View.GONE, mEmptyView);
        ViewUtils.setVisibility(View.GONE, mNoInternetView);
        ViewUtils.setVisibility(View.GONE, mHeaderEmptyView);
    }

    @Override
    public void hideNoInternetStub() {
        ViewUtils.setVisibility(View.VISIBLE, mRecyclerView);

        ViewUtils.setVisibility(View.GONE, mNoInternetView);
        ViewUtils.setVisibility(View.GONE, mHeaderEmptyView);
        ViewUtils.setVisibility(View.GONE, mEmptyView);
    }

    @Override
    public boolean showNoInternetStub() {
        // show only if we have no items in adapter
        if (mRecyclerView.getAdapter().getItemCount() == 0) {
            ViewUtils.setVisibility(View.VISIBLE, mNoInternetView);

            ViewUtils.setVisibility(View.INVISIBLE, mRecyclerView);
            ViewUtils.setVisibility(View.GONE, mEmptyView);
            ViewUtils.setVisibility(View.GONE, mHeaderEmptyView);
            return true;
        }
        return false;
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

    public abstract void tryAgain();
}
