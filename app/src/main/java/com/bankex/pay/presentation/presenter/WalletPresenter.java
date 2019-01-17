package com.bankex.pay.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.domain.interactor.IPayWalletInteractor;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.home.IWalletView;
import com.bankex.pay.presentation.ui.home.WalletFragment;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

/**
 * Presenter for {@link WalletFragment}.
 */
@InjectViewState
public class WalletPresenter extends BasePresenter<IWalletView> {
	private IPayWalletInteractor mPayWalletInteractor;
	private IRxSchedulersUtils mRxSchedulersUtils;

	public WalletPresenter(IPayWalletInteractor payWalletInteractor, IRxSchedulersUtils rxSchedulersUtils) {
		mPayWalletInteractor = payWalletInteractor;
		mRxSchedulersUtils = rxSchedulersUtils;
	}

	@Override
	public void attachView(IWalletView view) {
		super.attachView(view);
		loadWallet();
	}

	/**
	 * Load wallet from net.
	 */
	private void loadWallet() {
		getViewState().showLoading();
		getRxCompositeDisposable().add(mPayWalletInteractor.getWallet()
				.subscribeOn(mRxSchedulersUtils.getIOScheduler())
				.observeOn(mRxSchedulersUtils.getMainThreadScheduler())
				.subscribe(
						payWalletModel -> {
							getViewState().hideLoading();
							getViewState().loadData(payWalletModel);
						}, throwable -> getViewState().showError(throwable.getMessage())));
	}
}
