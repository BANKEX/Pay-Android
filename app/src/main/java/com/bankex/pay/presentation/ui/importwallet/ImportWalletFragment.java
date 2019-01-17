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
import butterknife.BindView;
import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import com.bankex.pay.presentation.ui.importwallet.adapter.ImportWalletFragmentPagerAdapter;

/**
 * View for import wallet screen.
 */
public class ImportWalletFragment extends BaseFragment {
	@BindView(R.id.import_wallet_view_pager) ViewPager mViewPager;
	@BindView(R.id.import_wallet_tab_layout) TabLayout mTabLayout;
	@BindView(R.id.add_contact_toolbar) Toolbar mToolbar;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return setAndBindContentView(inflater, container, R.layout.fragment_import_wallet);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews();
		initToolbar();
	}

	private void initViews() {
		mViewPager.setAdapter(new ImportWalletFragmentPagerAdapter(getChildFragmentManager(), getContext()));
		mTabLayout.setupWithViewPager(mViewPager);
	}

	private void initToolbar() {
		mToolbar.setTitle(string(R.string.import_wallet));
		mToolbar.setNavigationIcon(R.drawable.ic_arrow_left_black_24dp);
	}
}