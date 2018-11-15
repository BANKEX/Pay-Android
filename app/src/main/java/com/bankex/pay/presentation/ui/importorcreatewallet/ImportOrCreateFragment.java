package com.bankex.pay.presentation.ui.importorcreatewallet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.OnClick;
import com.bankex.pay.R;
import com.bankex.pay.di.importorcreatewallet.ImportOrCreateWalletInjector;
import com.bankex.pay.presentation.navigation.importorcreate.IImportWalletRouter;
import com.bankex.pay.presentation.ui.base.BaseFragment;
import javax.inject.Inject;

/**
 * View for import or create wallet screen.
 */
public class ImportOrCreateFragment extends BaseFragment {
	@Inject
	IImportWalletRouter importWalletRouter;

	@BindView(R.id.create_button) Button mCreateWallet;

	@Override public void onCreate(Bundle savedInstanceState) {
		ImportOrCreateWalletInjector.getImportOrCreateComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Nullable @Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return setAndBindContentView(inflater, container, R.layout.fragment_import_or_create_wallet);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mCreateWallet.setEnabled(false);
	}

	@OnClick(R.id.import_button)
	public void onImportButtonClicked() {
		importWalletRouter.openImportWalletScreen(getActivity());
	}

	@OnClick(R.id.create_button)
	public void onCreateWalletClicked() {
		importWalletRouter.openCreateWalletScreen(getActivity());
	}
}
