package com.bankex.pay.ui.navigation.importorcreate;

import android.content.Context;

/**
 * Роутер перехода с экрана Импорта или Создания
 *
 * @author Gevork Safaryan on 19.09.2018.
 */
public interface IImportWalletRouter {

    /**
     * Открыть экран импорта
     *
     * @param context {@link Context}
     */
    void openImportWalletScreen(Context context);

    /**
     * Открыть экран создания
     *
     * @param context {@link Context}
     */
    void openCreateWalletScreen(Context context);

    /**
     * Открыть главный экран
     *
     * @param context {@link Context}
     */
    void openMainActivityScreen(Context context);
}
