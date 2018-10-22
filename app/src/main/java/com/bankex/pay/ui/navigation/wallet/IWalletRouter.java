package com.bankex.pay.ui.navigation.wallet;

import android.content.Context;

/**
 * Роутер экрана wallet
 *
 * @author Pavel Apanovskiy on 12.09.2018.
 */
public interface IWalletRouter {

    /**
     * Открываем экран импорта/создания кошелька
     *
     * @param context context
     */
    void openImportOrCreate(Context context);
}
