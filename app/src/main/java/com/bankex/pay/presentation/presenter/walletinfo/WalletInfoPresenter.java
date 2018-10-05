package com.bankex.pay.presentation.presenter.walletinfo;


import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.data.reporitories.TransactionListRepository;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.walletinfo.IWalletInfoView;

/**
 * @author Denis Anisimov.
 */
@InjectViewState
public class WalletInfoPresenter extends BasePresenter<IWalletInfoView> {

    TransactionListRepository mTransactionListRepository;

    public WalletInfoPresenter(TransactionListRepository transactionListRepository) {
        mTransactionListRepository = transactionListRepository;
    }

    void fetchBalance() {

    }

    void fetchBalanceInUSD() {

    }
}
