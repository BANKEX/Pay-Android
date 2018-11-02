package com.bankex.pay.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.domain.interactor.IImportWalletFromPassPhraseInteractor;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.importwallet.passphrase.IImportPassPhraseView;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

/**
 * Презентер экрана импорта по фразе
 *
 * @author Pavel Apanovskiy on 09/10/2018.
 */
@InjectViewState
public class ImportPassPhrasePresenter extends BasePresenter<IImportPassPhraseView> {

	private final IImportWalletFromPassPhraseInteractor mImportWalletFromPassPhraseInteractor;
	private final IRxSchedulersUtils mRxSchedulersUtils;

	public ImportPassPhrasePresenter(IImportWalletFromPassPhraseInteractor importWalletFromPassPhraseInteractor,
			IRxSchedulersUtils rxSchedulersUtils) {
		mImportWalletFromPassPhraseInteractor = importWalletFromPassPhraseInteractor;
		mRxSchedulersUtils = rxSchedulersUtils;
	}

	public void importWalletFromPassPhrase(String passPhrase, String walletName) {
		mImportWalletFromPassPhraseInteractor.importWalletFromPassPhrase(passPhrase, walletName)
				.subscribeOn(mRxSchedulersUtils.getIOScheduler())
				.observeOn(mRxSchedulersUtils.getMainThreadScheduler())
				.subscribe(payWalletModel -> getViewState().doSomethingGood(),
						throwable -> getViewState().showError(throwable));
	}
}