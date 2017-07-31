package com.elegion.android.ui.features;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.elegion.android.data.Repository;
import com.elegion.android.data.model.Feature;
import com.elegion.android.ui.base.adapter.AbstractPaginationAdapter;
import com.elegion.android.ui.base.fragment.BaseRecyclerFragment;
import com.elegion.android.ui.features.adapter.FeaturesAdapter;

import java.util.List;

import timber.log.Timber;

/**
 * @author mikhail.barannikov on 24.07.2017
 */
public class FeaturesFragment extends BaseRecyclerFragment implements FeaturesView, AbstractPaginationAdapter.Callback {
    @InjectPresenter
    FeaturesPresenter mPresenter;

    private FeaturesAdapter mAdapter = new FeaturesAdapter();

    @ProvidePresenter
    FeaturesPresenter providePresenter() {
        return new FeaturesPresenter(Repository.get(getActivity()));
    }

    public static Fragment newInstance() {
        return new FeaturesFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter.setCallback(this);
    }

    @Override
    protected RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onRefresh() {
        mPresenter.loadFeatures(true);
    }

    @Override
    public void tryAgain() {
        mPresenter.loadFeatures(true);
    }

    @Override
    public void showFeatures(List<Feature> features, boolean clear) {
        Timber.d("MOXY: showFeatures(%s)", clear);
        mAdapter.setData(features, clear);
    }

    @Override
    public void clearFeatures() {
        // pass
        Timber.d("MOXY: clearFeatures()");
    }

    @Override
    public void loadMore() {
        mPresenter.loadFeatures(false);
    }
}
