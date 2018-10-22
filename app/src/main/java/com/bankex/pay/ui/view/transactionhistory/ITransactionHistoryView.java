package com.bankex.pay.ui.view.transactionhistory;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.ui.view.base.BaseView;

/**
 * Interface for transaction history view {@link TransactionHistoryFragment}
 *
 * @author Pavel Apanovskiy on 27/09/2018.
 */
public interface ITransactionHistoryView extends BaseView {

	/**
	 * Method showing toast message just one first time
	 */
	@StateStrategyType(OneExecutionStateStrategy.class)
	void showToast();
}
