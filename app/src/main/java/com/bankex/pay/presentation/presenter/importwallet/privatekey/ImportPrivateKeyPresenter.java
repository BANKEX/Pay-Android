package com.bankex.pay.presentation.presenter.importwallet.privatekey;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.domain.interactor.IImportWalletFromPrivateKeyInteractor;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.importwallet.privatekey.IImportPrivateKeyView;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

/**
 * Презентер для экрана импорта по ключу
 *
 * @author Pavel Apanovskiy on 09/10/2018.
 */
@InjectViewState
public class ImportPrivateKeyPresenter extends BasePresenter<IImportPrivateKeyView> {

    private final IImportWalletFromPrivateKeyInteractor mImportWalletFromPrivateKeyInteractor;
    private final IRxSchedulersUtils mRxSchedulersUtils;

    public ImportPrivateKeyPresenter(IImportWalletFromPrivateKeyInteractor importWalletFromPrivateKeyInteractor,
                                     IRxSchedulersUtils rxSchedulersUtils) {
        mImportWalletFromPrivateKeyInteractor = importWalletFromPrivateKeyInteractor;
        mRxSchedulersUtils = rxSchedulersUtils;
    }

    public void magic(String privateKey, String walletName) {
        mImportWalletFromPrivateKeyInteractor.importStoreByPrivateKey(privateKey, walletName)
                .subscribeOn(mRxSchedulersUtils.getIOScheduler())
                .observeOn(mRxSchedulersUtils.getMainThreadScheduler())
                .subscribe(payWalletModel -> getViewState().doSomethingGood(),
                        throwable -> getViewState().showError(throwable));
    }
}
