package com.bankex.pay.di.transactionhistory;

import com.bankex.pay.presentation.presenter.TransactionHistoryPresenter;
import com.bankex.pay.presentation.navigation.transactionhistory.ITransactionHistoryRouter;
import com.bankex.pay.presentation.navigation.transactionhistory.TransactionHistoryRouter;

import dagger.Module;
import dagger.Provides;

/**
 * Module for transaction history screen.
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
