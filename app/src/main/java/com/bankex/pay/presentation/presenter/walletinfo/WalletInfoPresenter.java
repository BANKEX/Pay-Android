package com.bankex.pay.presentation.presenter.walletinfo;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.data.entity.TickerEntity;
import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.ErrorMessage;
import com.bankex.pay.domain.interactor.address.ISearchByAddressInteractor;
import com.bankex.pay.domain.interactor.cryptocompare.IExchangeRateInteractor;
import com.bankex.pay.domain.interactor.transactions.ITransactionListInteractor;
import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.address.AddressModel;
import com.bankex.pay.domain.models.network.BaseHeadResponse;
import com.bankex.pay.domain.models.transaction.ListType;
import com.bankex.pay.domain.models.transaction.ModuleDestinationType;
import com.bankex.pay.domain.models.transaction.TransactionModel;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.walletinfo.IWalletInfoView;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

import java.util.List;

/**
 * @author Denis Anisimov.
 */
@InjectViewState
public class WalletInfoPresenter extends BasePresenter<IWalletInfoView> {

    private static final int PAGE = 1;
    private static final int TRANSACTIONS_LIST_SIZE = 5;

    private final ISearchByAddressInteractor mSearchByAddressInteractor;
    private final IExchangeRateInteractor mIExchangeRateInteractor;
    private final ITransactionListInteractor mTransactionListInteractor;
    private final IRxSchedulersUtils mRxSchedulersUtils;

    public WalletInfoPresenter(ISearchByAddressInteractor searchByAddressInteractor, IExchangeRateInteractor iExchangeRateInteractor, ITransactionListInteractor transactionListInteractor, IRxSchedulersUtils rxSchedulersUtils) {
        mSearchByAddressInteractor = searchByAddressInteractor;
        mIExchangeRateInteractor = iExchangeRateInteractor;
        mTransactionListInteractor = transactionListInteractor;
        mRxSchedulersUtils = rxSchedulersUtils;
    }

    public void fetchBalance(String address) {
        mSearchByAddressInteractor.findByAddress(address)
                .subscribeOn(mRxSchedulersUtils.getIOScheduler())
                .observeOn(mRxSchedulersUtils.getMainThreadScheduler())
                .subscribe(result -> checkResult(result, address),
                        this::checkError);
    }

    public void fetchExchangeRate(String tiker) {
        mIExchangeRateInteractor.getExhangeRateToUSD(tiker)
                .subscribeOn(mRxSchedulersUtils.getIOScheduler())
                .observeOn(mRxSchedulersUtils.getMainThreadScheduler())
                .subscribe(result -> chandleExchangeRate(result, tiker),
                        this::checkError);
    }

    private void chandleExchangeRate(BaseHeadResponse<TickerEntity> result, String tiker) {

    }

    private void checkError(Throwable throwable) {
        getViewState().showError("No internet\nconnection",
                "Try to search later",
                throwable);
    }

    private void checkResult(BaseDomainBean<List<BaseBankexModel>> listBaseDomainBean, String text) {
        ErrorMessage errorObject = listBaseDomainBean.getErrorObject();
        boolean haveErrors = errorObject != null && errorObject.hasErros();
        List<BaseBankexModel> successObject = listBaseDomainBean.getSuccessObject();
        if (successObject != null && !haveErrors) {
            AddressModel addressModel = (AddressModel) successObject.get(0);
            fetchExchangeRate(text);
            getViewState().setBalanceToAppBar(String.valueOf(addressModel.getBalance()));
            mTransactionListInteractor.loadTransactions(ListType.ETH,
                    ModuleDestinationType.ADDRESS,
                    text,
                    PAGE,
                    TRANSACTIONS_LIST_SIZE)
                    .subscribeOn(mRxSchedulersUtils.getIOScheduler())
                    .observeOn(mRxSchedulersUtils.getMainThreadScheduler())
                    .subscribe(throwable -> updateAdapter(successObject));
        }
        if (haveErrors) {
            getViewState().showError("No results\nfound", "Try searching something else", null);
        }
    }

    private void updateAdapter(List<BaseBankexModel> successObject) {
       getViewState().setTransactionsPreview(successObject);
    }

    void fetchBalanceInUSD() {

    }
}
