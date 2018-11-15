package com.bankex.pay.presentation.navigation.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import com.bankex.pay.R;
import com.bankex.pay.presentation.navigation.base.BaseRouter;
import com.bankex.pay.presentation.navigation.contacts.IContactsRouter;
import com.bankex.pay.presentation.ui.contacts.ContactsFragment;
import com.bankex.pay.presentation.ui.home.SettingsFragment;
import com.bankex.pay.presentation.ui.home.WalletFragment;
import com.bankex.pay.presentation.ui.importorcreatewallet.ImportOrCreateActivity;
import com.bankex.pay.presentation.ui.transactionhistory.TransactionHistoryFragment;

/**
 * Am implementation of {@link IContactsRouter}.
 */
public class MainRouter extends BaseRouter implements IMainRouter {

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void goToWalletTab(FragmentActivity activity) {
		runFragmentWithAnimation(activity, new WalletFragment(), R.id.fragment_container);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void goToHistoryTab(FragmentActivity activity) {
		runFragmentWithAnimation(activity, new TransactionHistoryFragment(), R.id.fragment_container);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void goToContactsTab(FragmentActivity activity) {
		runFragmentWithAnimation(activity, new ContactsFragment(), R.id.fragment_container);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void goToSettingsTab(FragmentActivity activity) {
		runFragmentWithAnimation(activity, new SettingsFragment(), R.id.fragment_container);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public void openImportOrCreate(Context context) {
		Intent intent = ImportOrCreateActivity.newIntent(context);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
}
