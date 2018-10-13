package com.bankex.pay.presentation.ui.walletinfo;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.transaction.TransactionModel;
import com.bankex.pay.presentation.ui.view.base.BaseView;

import java.util.List;

/**
 * Интерфейс для вьюхи подробностей кошелька
 *
 * @author Denis Anisimov.3
 */
public interface IWalletInfoView extends BaseView {

    /**
     * Установка занчение баланса на апп бар
     *
     * @param value
     */
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setBalanceToAppBar(String value);

    /**
     * Установка значение балансас в долларах на апп бар
     *
     * @param balanceInUSD
     */
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setBalanceInUSD(String balanceInUSD);

    /**
     * Установка первых пяти значений из списка транзакций по данному адресу
     *
     * @param transactionsPreview
     */
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setTransactionsPreview(List<BaseBankexModel> transactionsPreview);

    /**
     * Показать ошибку запроса
     *
     * @param error текст ошибки
     */
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(String error, String description, Throwable throwable);
}
