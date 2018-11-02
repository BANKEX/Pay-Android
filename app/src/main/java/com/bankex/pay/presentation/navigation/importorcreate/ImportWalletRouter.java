package com.bankex.pay.presentation.navigation.importorcreate;

import android.content.Context;
import android.content.Intent;
import com.bankex.pay.presentation.ui.importwallet.ImportWalletActivity;
import com.bankex.pay.presentation.ui.mainscreen.MainScreenActivity;

/**
 * Router to navigate into fragments from Import of Create Wallet screen.
 */
public class ImportWalletRouter implements IImportWalletRouter {

	/**
	 * Open import wallet screen.
	 *
	 * @param context {@link Context}
	 */
	@Override
	public void openImportWalletScreen(Context context) {
		context.startActivity(new Intent(context, ImportWalletActivity.class));
	}

	/**
	 * Open create wallet screen.
	 *
	 * @param context {@link Context}
	 */
	@Override
	public void openCreateWalletScreen(Context context) {
		// TODO add when create wallet functional will be ready.
	}

	/**
	 * Start MainActivity.
	 *
	 * @param context {@link Context}
	 */
	@Override
	public void startMainActivityScreen(Context context) {
		context.startActivity(new Intent(context, MainScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
	}
}
