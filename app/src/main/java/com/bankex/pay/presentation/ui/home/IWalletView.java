package com.bankex.pay.presentation.ui.home;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.model.domain.PayWalletModel;
import com.bankex.pay.presentation.ui.view.base.BaseView;

/**
 * Интерфейс вью главного экрана
 *
 * @author Gevork Safaryan on 15.10.2018
 */
@StateStrategyType(AddToEndStrategy.class)
public interface IWalletView extends BaseView {

    /**
     * Показать загрузку
     */
    void showLoading();

    /**
     * Скрыть загрузку
     */
    void hideLoading();

    /**
     * Показать ошибку
     *
     * @param error
     */
    void showError(String error);

    /**
     * ПОказать пустую вью
     */
    void showEmptyView();

    /**
     * Отобразить данные по кошельку
     *
     * @param payWalletModel
     */
    void loadData(PayWalletModel payWalletModel);
}
