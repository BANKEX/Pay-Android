package com.bankex.pay.presentation.ui.importorcreatewallet;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.bankex.pay.R;
import com.bankex.pay.di.importorcreatewallet.ImportOrCreateWalletInjector;
import com.bankex.pay.presentation.navigation.importorcreate.IImportWalletRouter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import javax.inject.Inject;

/**
 * View for impoet or create wallet screen.
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
		ImportOrCreateWalletInjector.getImportOrCreateComponent().inject(this);
		super.onAttach(context);
	}

	private void initViews(View view) {
		mImportWallet = view.findViewById(R.id.import_button);
		mImportWallet.setOnClickListener(view2 -> onImportWalletClick());
		mCreateWallet = view.findViewById(R.id.create_button);
		mCreateWallet.setEnabled(false);
		mCreateWallet.setOnClickListener(view1 -> onCreateWalletClick());
	}

	private void onCreateWalletClick() {
		importWalletRouter.openCreateWalletScreen(getActivity());
	}

	private void onImportWalletClick() {
		importWalletRouter.openImportWalletScreen(getActivity());
	}
}
