package com.elegion.android.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.android.R;
import com.elegion.android.ui.widget.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Mike
 */
public abstract class BaseRecyclerFragment extends BaseRefresherFragment {
    @BindView(R.id.recycler)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.empty_view)
    protected EmptyView mEmptyView;

    protected RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        initRecycler();
        return view;
    }

    @Override
    protected boolean shouldShowNoInternetStubView() {
        return mRecyclerView.getAdapter().getItemCount() == 0;
    }

    protected int getLayout() {
        return R.layout.fr_recycler;
    }

    @CallSuper
    protected void initRecycler() {
        mLayoutManager = getLayoutManager();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(getAdapter());
    }

    @NonNull
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    protected abstract RecyclerView.Adapter getAdapter();

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

    @Override
    protected View[] getViews() {
        return new View[]{mRecyclerView};
    }
}
