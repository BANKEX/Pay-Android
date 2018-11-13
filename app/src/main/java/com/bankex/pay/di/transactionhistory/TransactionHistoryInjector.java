package com.bankex.pay.di.transactionhistory;

import com.bankex.pay.di.mainscreen.MainScreenInjector;

/**
 * Injector for {@link TransactionHistoryComponent}.
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
