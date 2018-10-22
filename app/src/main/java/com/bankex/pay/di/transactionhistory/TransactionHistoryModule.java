package com.bankex.pay.di.transactionhistory;

import com.bankex.pay.ui.presenter.transactionhistory.TransactionHistoryPresenter;
import com.bankex.pay.ui.navigation.transactionhistory.ITransactionHistoryRouter;
import com.bankex.pay.ui.navigation.transactionhistory.TransactionHistoryRouter;
import dagger.Module;
import dagger.Provides;

/**
 * Module for Transaction History screen
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
