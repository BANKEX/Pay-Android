package com.bankex.pay.ui.view.importorcreate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.bankex.pay.R;
import com.bankex.pay.di.importorcreate.ImportOrCreateInjector;
import com.bankex.pay.ui.navigation.importorcreate.IImportWalletRouter;
import com.bankex.pay.ui.view.base.BaseFragment;
import javax.inject.Inject;

/**
 * Import or create wallet fragment
 *
 * @author Gevork Safaryan on 18.09.2018
 */
public class ImportOrCreateFragment extends BaseFragment {
	@Inject
	IImportWalletRouter importWalletRouter;

	private Button mImportWallet;
	private Button mCreateWallet;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_import_or_create_wallet, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initViews(view);
	}

	@Override
	public void onAttach(Context context) {
		ImportOrCreateInjector.getImportOrCreateComponent().inject(this);
		super.onAttach(context);
	}

	/**
	 * Screen elements initialization
	 *
	 * @param view parent view {@link View}
	 */
	private void initViews(View view) {
		mImportWallet = view.findViewById(R.id.import_button);
		mImportWallet.setOnClickListener(v -> onImportWalletClick());
		mCreateWallet = view.findViewById(R.id.create_button);
		mCreateWallet.setOnClickListener(v -> onCreateWalletClicked());
	}

	/**
	 * On create wallet button clicked
	 */
	private void onCreateWalletClicked() {
		importWalletRouter.openCreateWalletScreen(getActivity());
	}

	/**
	 * On import wallet button clicked
	 */
	private void onImportWalletClick() {
		importWalletRouter.openImportWalletScreen(getActivity());
	}
}
