package com.bankex.pay.presentation.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.BuildConfig;
import com.bankex.pay.R;
import com.bankex.pay.di.wallet.WalletInjector;
import com.bankex.pay.domain.analytics.IAnalyticsManager;
import com.bankex.pay.domain.model.BaseBankexModel;
import com.bankex.pay.domain.model.BaseTitleModel;
import com.bankex.pay.domain.model.PayWalletModel;
import com.bankex.pay.domain.model.WalletCardModel;
import com.bankex.pay.presentation.adapter.wallet.WalletAdapter;
import com.bankex.pay.presentation.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.presenter.WalletPresenter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * View for wallet screen.
 */
public class WalletFragment extends BaseFragment implements IWalletView {
	@Inject
	IWalletRouter mRouter;

	@Inject
	@InjectPresenter
	WalletPresenter mWalletPresenter;

	@Inject
	IAnalyticsManager mAnalyticsManager;

	@BindView(R.id.create_button) Button mImportOrCreateButton;
	@BindView(R.id.wallet_recycler_view) RecyclerView mRecyclerView;

	private WalletAdapter mWalletAdapter;

	@ProvidePresenter
	public WalletPresenter provideWalletPresenter() {
		return mWalletPresenter;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		WalletInjector.getWalletComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return setAndBindContentView(inflater, container, R.layout.fragment_wallet);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mImportOrCreateButton.setVisibility(BuildConfig.DEBUG ? View.VISIBLE : View.GONE);
		setAdapterParameters();
	}

	@Override
	public void showLoading() {

	}

	@Override
	public void hideLoading() {

	}

	@Override
	public void showError(String error) {

	}

	@Override
	public void showEmptyView() {

	}

	@Override
	public void loadData(PayWalletModel payWalletModel) {
		List<BaseBankexModel> baseBankexModelList = new ArrayList<>();

		BaseTitleModel baseTitleModel = new BaseTitleModel();
		baseTitleModel.setTitle(payWalletModel.getName());
		baseTitleModel.setAddButtonTitle("ADD");
		baseBankexModelList.add(baseTitleModel);

		WalletCardModel walletCardModel = new WalletCardModel();
		walletCardModel.setTitle(payWalletModel.getAddress());
		walletCardModel.setValue(BigInteger.valueOf(0));
		baseBankexModelList.add(walletCardModel);

		mWalletAdapter.setItems(baseBankexModelList);
	}

	@OnClick(R.id.create_button)
	public void onCreateButtonClicked() {
		mRouter.openImportOrCreate(getContext());
	}

	private void setAdapterParameters() {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mWalletAdapter = new WalletAdapter(getActivity(), mRouter);
		mRecyclerView.setAdapter(mWalletAdapter);
	}
}
