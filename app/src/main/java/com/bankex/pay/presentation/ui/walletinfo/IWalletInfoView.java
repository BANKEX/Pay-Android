package com.bankex.pay.presentation.ui.walletinfo;

import com.bankex.pay.presentation.ui.view.base.BaseView;

/**
 * Интерфейс для вьюхи подробностей кошелька
 *
 * @author Denis Anisimov.
 */
public interface IWalletInfoView extends BaseView {

    void setBalanceToAppBar(String value);

    void setBalanceInUSD(String balanceInUSD);
}
