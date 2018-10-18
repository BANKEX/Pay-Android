package com.bankex.pay.presentation.navigation.importorcreate;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.presentation.view.importwallet.ImportWalletActivity;

/**
 * Роутер перехода с экрана Импорта или Создания
 *
 * @author Gevork Safaryan on 19.09.2018.
 */
public class ImportWalletRouter implements IImportWalletRouter {

    /**
     * Открыть экран импорта
     *
     * @param context {@link Context}
     */
    @Override
    public void openImportWalletScreen(Context context) {
        context.startActivity(new Intent(context, ImportWalletActivity.class));
    }

    /**
     * Открыть экран создания
     *
     * @param context {@link Context}
     */
    @Override
    public void openCreateWalletScreen(Context context) {
        //todo
    }
}
