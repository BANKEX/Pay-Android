package com.bankex.pay.presentation.ui.navigation.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.importorcreatewallet.ImportOrCreateActivity;
import com.bankex.pay.presentation.ui.navigation.base.BaseRouter;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;
import com.bankex.pay.presentation.ui.view.contacts.ContactsFragment;
import com.bankex.pay.presentation.ui.view.transactionhistory.TransactionHistoryFragment;

/**
 * @author Pavel Apanovskiy on 12.09.2018.
 */
public class MainRouter extends BaseRouter implements IMainRouter {

    @Override
    public void goToWalletTab(FragmentActivity activity,
                              BaseFragment fragment) {
        runFragmentWithAnimation(activity,
                fragment,
                R.id.fragment_container);
    }

    @Override
    public void goToHistoryTab(FragmentActivity activity) {
        runFragmentWithAnimation(activity,
                TransactionHistoryFragment.newInstance(),
                R.id.fragment_container);
    }

    @Override
    public void goToContactsTab(FragmentActivity activity) {
        runFragmentWithAnimation(activity,
                ContactsFragment.newInstance(),
                R.id.fragment_container);
    }

    @Override
    public void goToSettingsTab(FragmentActivity activity,
                                Fragment fragment) {
        runFragmentWithAnimation(activity,
                fragment,
                R.id.fragment_container);
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
