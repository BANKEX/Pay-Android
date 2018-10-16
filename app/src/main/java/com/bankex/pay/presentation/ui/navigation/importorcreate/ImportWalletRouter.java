package com.bankex.pay.presentation.ui.navigation.importorcreate;

import android.content.Context;
import android.content.Intent;

import com.bankex.pay.presentation.ui.importwallet.ImportWalletActivity;
import com.bankex.pay.presentation.ui.mainscreen.MainScreenActivity;

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

    @Override
    public void openMainActivityScreen(Context context) {
        context.startActivity(new Intent(context, MainScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
