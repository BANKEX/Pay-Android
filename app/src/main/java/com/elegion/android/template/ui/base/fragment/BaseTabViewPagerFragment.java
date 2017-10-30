package com.elegion.android.template.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.android.template.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Mike
 */
public abstract class BaseTabViewPagerFragment extends BaseFragment {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    protected int getLayout() {
        return R.layout.fr_toolbar_tab_viewpager;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewPager.setAdapter(getAdapter());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    abstract PagerAdapter getAdapter();

    public TabLayout getTabLayout() {
        return mTabLayout;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

}
