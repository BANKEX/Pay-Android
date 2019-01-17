package com.bankex.pay.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.domain.interactor.IPayWalletInteractor;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.mainscreen.IMainScreenView;
import com.bankex.pay.presentation.ui.mainscreen.MainScreenActivity;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;
import io.reactivex.disposables.Disposable;

/**
 * Presenter for {@link MainScreenActivity}.
 */
@InjectViewState
public class MainScreenPresenter extends BasePresenter<IMainScreenView> {
	private final IPayWalletInteractor mPayWalletInteractor;
	private final IRxSchedulersUtils mRxSchedulersUtils;

	public MainScreenPresenter(IPayWalletInteractor payWalletInteractor, IRxSchedulersUtils rxSchedulersUtils) {
		mPayWalletInteractor = payWalletInteractor;
		mRxSchedulersUtils = rxSchedulersUtils;
	}

	/**
	 * Method to check if onboarding has shown before.
	 *
	 * @param status if has shown
	 */
	public void checkOnboardingStatus(boolean status) {
		if (!status) {
			getViewState().showOnboarding();
		} else {
			// TODO: 13/10/2018 пока фича не готова - гасим
			//getViewState().showLockScreen();
			checkPayWallet();
		}
	}

	public void checkPayWallet() {
		Disposable disposable = mPayWalletInteractor.getWallet()
				.subscribeOn(mRxSchedulersUtils.getIOScheduler())
				.observeOn(mRxSchedulersUtils.getMainThreadScheduler())
				.subscribe(payWalletModel -> {

				}, throwable -> getViewState().openImportOrCreate());
	}
}
