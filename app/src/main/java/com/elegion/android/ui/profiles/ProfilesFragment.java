package com.elegion.android.ui.profiles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.elegion.android.data.Repository;
import com.elegion.android.data.model.UserProfile;
import com.elegion.android.ui.base.adapter.AbstractPaginationAdapter;
import com.elegion.android.ui.base.fragment.BaseRecyclerFragment;
import com.elegion.android.ui.profiles.adapter.ProfilesAdapter;

import java.util.List;

/**
 * @author mikhail.barannikov on 24.07.2017
 */
public class ProfilesFragment extends BaseRecyclerFragment implements ProfilesView, AbstractPaginationAdapter.Callback {
    @InjectPresenter
    ProfilesPresenter mPresenter;

    private ProfilesAdapter mAdapter = new ProfilesAdapter();

    @ProvidePresenter
    ProfilesPresenter providePresenter() {
        return new ProfilesPresenter(Repository.get(getActivity()));
    }

    public static Fragment newInstance() {
        return new ProfilesFragment();
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
        mPresenter.loadProfiles(true);
    }

    @Override
    public void tryAgain() {
        mPresenter.loadProfiles(true);
    }

    @Override
    public void showProfiles(List<UserProfile> profiles, boolean clear) {
        mAdapter.setData(profiles, clear);
    }

    @Override
    public void loadMore() {
        mPresenter.loadProfiles(false);
    }
}
