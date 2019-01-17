package com.bankex.pay.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.transactionhistory.ITransactionHistoryView;
import com.bankex.pay.presentation.ui.transactionhistory.TransactionHistoryFragment;

/**
 * Presenter for {@link TransactionHistoryFragment}.
 */
@InjectViewState
public class TransactionHistoryPresenter extends BasePresenter<ITransactionHistoryView> {

	public TransactionHistoryPresenter() {
	}
}
