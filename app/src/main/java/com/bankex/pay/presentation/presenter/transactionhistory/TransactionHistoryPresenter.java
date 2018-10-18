package com.bankex.pay.presentation.presenter.transactionhistory;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.view.transactionhistory.ITransactionHistoryView;

/**
 * Presenter for transactions history screen
 *
 * @author Pavel Apanovskiy on 27/09/2018.
 */
@InjectViewState
public class TransactionHistoryPresenter extends BasePresenter<ITransactionHistoryView> {

	public TransactionHistoryPresenter() {
	}

	public void doMagic() {
		getViewState().showToast();
	}
}
