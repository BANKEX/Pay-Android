package com.bankex.pay.domain.navigation.wallet;

import android.content.Context;

/**
 * Роутер экрана wallet
 *
 * @author Pavel Apanovskiy on 12.09.2018.
 */
public interface IWalletRouter {

    /**
     * Метод перехода на экран Импорта Создания
     *
     * @param context контекст
     */
    void openImportOrCreate(Context context);
}
