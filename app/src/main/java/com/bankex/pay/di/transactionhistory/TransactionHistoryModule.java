package com.bankex.pay.di.transactionhistory;

import com.bankex.pay.presentation.presenter.transactionhistory.TransactionHistoryPresenter;
import com.bankex.pay.presentation.navigation.transactionhistory.ITransactionHistoryRouter;
import com.bankex.pay.presentation.navigation.transactionhistory.TransactionHistoryRouter;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль экрана истории транзакций
 *
 * @author Pavel Apanovskiy on 27/09/2018.
 */
@Module
public class TransactionHistoryModule {

    @Provides
    @TransactionHistoryScope
    ITransactionHistoryRouter provideTransactionHistoryRouter() {
        return new TransactionHistoryRouter();
    }

    @Provides
    @TransactionHistoryScope
    TransactionHistoryPresenter provideTransactionHistoryPresenter() {
        return new TransactionHistoryPresenter();
    }
}
