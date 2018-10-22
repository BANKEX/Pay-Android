package com.bankex.pay.presentation.ui.navigation.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.bankex.pay.presentation.ui.view.base.BaseFragment;

/**
 * @author Pavel Apanovskiy on 12.09.2018.
 */
public interface IMainRouter {

    void goToWalletTab(FragmentActivity activity,
                       BaseFragment fragment);

    void goToHistoryTab(FragmentActivity activity);

    void goToContactsTab(FragmentActivity activity);

    void goToSettingsTab(FragmentActivity activity,
                         Fragment fragment);

    /**
     * Открываем экран импорта/создания кошелька
     *
     * @param context context
     */
    void openImportOrCreate(Context context);
}
