package com.bankex.pay.di.transactionhistory;

import com.bankex.pay.presentation.ui.transactionhistory.TransactionHistoryFragment;

import dagger.Subcomponent;

/**
 * Сабкомпонент экрана истории транзакций
 *
 * @author Pavel Apanovskiy on 27/09/2018.
 */
@Subcomponent(modules = {TransactionHistoryModule.class})
@TransactionHistoryScope
public interface TransactionHistoryComponent {

    @Subcomponent.Builder
    interface Builder {
        TransactionHistoryComponent.Builder makeTransactionHistoryModule(TransactionHistoryModule module);

        TransactionHistoryComponent build();
    }

    void inject(TransactionHistoryFragment fragment);
}
