package com.bankex.pay.presentation.navigation.importorcreate;

import android.content.Context;
import android.content.Intent;
import com.bankex.pay.presentation.ui.importwallet.ImportWalletActivity;
import com.bankex.pay.presentation.ui.mainscreen.MainScreenActivity;

/**
 * Am implementation of {@link IImportWalletRouter}.
 */
public class ImportWalletRouter implements IImportWalletRouter {

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void openImportWalletScreen(Context context) {
		context.startActivity(new Intent(context, ImportWalletActivity.class));
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void openCreateWalletScreen(Context context) {
		// TODO add when create wallet functional will be ready.
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void startMainActivityScreen(Context context) {
		context.startActivity(new Intent(context, MainScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
	}
}
