package com.bankex.pay.di.transactionhistory;

import com.bankex.pay.presentation.ui.transactionhistory.TransactionHistoryFragment;
import dagger.Subcomponent;

/**
 * Sub component for transaction history screen.
 */
@Subcomponent(modules = { TransactionHistoryModule.class })
@TransactionHistoryScope
public interface TransactionHistoryComponent {
	@Subcomponent.Builder
	interface Builder {
		TransactionHistoryComponent.Builder makeTransactionHistoryModule(TransactionHistoryModule module);

		TransactionHistoryComponent build();
	}

	void inject(TransactionHistoryFragment fragment);
}
