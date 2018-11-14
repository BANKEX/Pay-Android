package com.bankex.pay.presentation.ui.home;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.domain.model.PayWalletModel;
import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * View interface for wallet main screen.
 */
@StateStrategyType(AddToEndStrategy.class)
public interface IWalletView extends BaseView {

    /**
     * Method to show loading.
     */
    void showLoading();

    /**
     * Method to hide loading.
     */
    void hideLoading();

    /**
     * Method to show error to user.
     *
     * @param error error message String
     */
    void showError(String error);

    /**
     * Method to show empty list.
     */
    void showEmptyView();

    /**
     * Method to init view with data.
     *
     * @param payWalletModel model with data
     */
    void loadData(PayWalletModel payWalletModel);
}
