package com.bankex.pay.presentation.view.transactionhistory;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.presentation.view.base.BaseView;

/**
 * Интерфейс вью для экрана истории транзакций {@link TransactionHistoryFragment}
 *
 * @author Pavel Apanovskiy on 27/09/2018.
 */
public interface ITransactionHistoryView extends BaseView {

    /**
     * Показать Toast
     */
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showToast();
}
