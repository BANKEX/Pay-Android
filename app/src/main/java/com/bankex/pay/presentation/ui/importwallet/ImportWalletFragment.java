package com.bankex.pay.presentation.ui.importwallet;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.importwallet.adapter.ImportWalletFragmentPagerAdapter;
import com.bankex.pay.presentation.ui.base.BaseFragment;

/**
 * Экран импорта кошелька
 *
 * @author Gevork Safaryan on 19.09.2018
 */
public class ImportWalletFragment extends BaseFragment {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    public static ImportWalletFragment newInstance() {
        return new ImportWalletFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.import_wallet_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initViews(View view) {
        mViewPager = view.findViewById(R.id.import_wallet_view_pager);
        mTabLayout = view.findViewById(R.id.import_wallet_tab_layout);

        mViewPager.setAdapter(new ImportWalletFragmentPagerAdapter(getChildFragmentManager(), getContext()));
        mTabLayout.setupWithViewPager(mViewPager);

        initToolbar(view);
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(getContext().getString(R.string.import_wallet));
        toolbar.setNavigationIcon(getContext().getDrawable(R.drawable.ic_arrow_left_black_24dp));
    }
}