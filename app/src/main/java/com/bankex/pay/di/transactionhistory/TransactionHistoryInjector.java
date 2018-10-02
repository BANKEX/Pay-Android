package com.bankex.pay.di.transactionhistory;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Инжектор компонента {@link TransactionHistoryComponent}
 *
 * @author Pavel Apanovskiy on 27/09/2018.
 */
public class TransactionHistoryInjector {

    private static TransactionHistoryComponent sTransactionHistoryComponent;

    public static TransactionHistoryComponent getTransactionHistoryComponent() {
        if (sTransactionHistoryComponent == null) {
            sTransactionHistoryComponent = MainScreenInjector.getMainScreenComponent()
                    .plusTransactionHistoryComponent()
                    .makeTransactionHistoryModule(new TransactionHistoryModule())
                    .build();
        }
        return sTransactionHistoryComponent;
    }

    public static void clearTransactionHistoryComponent() {
        sTransactionHistoryComponent = null;
    }
}
