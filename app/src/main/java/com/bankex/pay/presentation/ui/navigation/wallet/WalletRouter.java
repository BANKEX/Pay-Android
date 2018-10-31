package com.bankex.pay.presentation.ui.navigation.wallet;

import android.content.Context;

import com.bankex.pay.presentation.ui.importorcreatewallet.ImportOrCreateActivity;
import com.bankex.pay.presentation.ui.navigation.base.BaseRouter;

/**
 * Реализация роутера экрана wallet {@link IWalletRouter}
 *
 * @author Pavel Apanovskiy on 12.09.2018.
 */
public class WalletRouter extends BaseRouter implements IWalletRouter {

    /**
     * Метод перехода на экран Импорта Создания
     *
     * @param context контекст
     */
    @Override
    public void openImportOrCreate(Context context) {
        context.startActivity(ImportOrCreateActivity.newIntent(context));
    }
}
