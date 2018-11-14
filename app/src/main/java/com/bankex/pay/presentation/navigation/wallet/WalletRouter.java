package com.bankex.pay.presentation.navigation.wallet;

import android.content.Context;
import com.bankex.pay.presentation.navigation.base.BaseRouter;
import com.bankex.pay.presentation.ui.importorcreatewallet.ImportOrCreateActivity;

/**
 * An implementation of {@link IWalletRouter}.
 */
public class WalletRouter extends BaseRouter implements IWalletRouter {

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void openImportOrCreate(Context context) {
		context.startActivity(ImportOrCreateActivity.newIntent(context));
	}
}
