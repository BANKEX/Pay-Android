package com.bankex.pay.presentation.presenter.walletinfo;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.ErrorMessage;
import com.bankex.pay.domain.interactor.address.ISearchByAddressInteractor;
import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.address.AddressModel;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.walletinfo.IWalletInfoView;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

import java.util.List;

/**
 * @author Denis Anisimov.
 */
@InjectViewState
public class WalletInfoPresenter extends BasePresenter<IWalletInfoView> {

    private final ISearchByAddressInteractor mSearchByAddressInteractor;
    private final IRxSchedulersUtils mRxSchedulersUtils;

    public WalletInfoPresenter(ISearchByAddressInteractor searchByAddressInteractor, IRxSchedulersUtils rxSchedulersUtils) {
        mSearchByAddressInteractor = searchByAddressInteractor;
        mRxSchedulersUtils = rxSchedulersUtils;
    }

    public void fetchBalance(String address) {
        mSearchByAddressInteractor.findByAddress(address)
                .subscribeOn(mRxSchedulersUtils.getIOScheduler())
                .observeOn(mRxSchedulersUtils.getMainThreadScheduler())
                .subscribe(result -> checkResult(result, address),
                        this::checkError);
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
            getViewState().setBalanceToAppBar(String.valueOf(addressModel.getBalance()));
        }
        if (haveErrors) {
            getViewState().showError("No results\nfound", "Try searching something else", null);
        }
    }

    void fetchBalanceInUSD() {

    }
}
