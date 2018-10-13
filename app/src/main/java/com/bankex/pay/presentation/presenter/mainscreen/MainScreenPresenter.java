package com.bankex.pay.presentation.presenter.mainscreen;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.domain.interactor.IPayWalletInteractor;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.mainscreen.IMainScreenView;
import com.bankex.pay.presentation.ui.mainscreen.MainScreenActivity;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

/**
 * Презентер корневой активити {@link MainScreenActivity}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@InjectViewState
public class MainScreenPresenter extends BasePresenter<IMainScreenView> {

    private final IPayWalletInteractor mPayWalletInteractor;
    private final IRxSchedulersUtils mRxSchedulersUtils;

    public MainScreenPresenter(IPayWalletInteractor payWalletInteractor,
                               IRxSchedulersUtils rxSchedulersUtils) {
        mPayWalletInteractor = payWalletInteractor;
        mRxSchedulersUtils = rxSchedulersUtils;
    }

    /**
     * Проверяем статус - показывали ли онбординг
     *
     * @param status cтатус boolean
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
        mPayWalletInteractor.getWallet()
                .subscribeOn(mRxSchedulersUtils.getIOScheduler())
                .observeOn(mRxSchedulersUtils.getMainThreadScheduler())
                .subscribe((payWalletModel, throwable) -> {
                    if (throwable instanceof NullPointerException){
                        getViewState().openImportOrCreate();
                    }else {
                        // TODO: 13/10/2018

                        //do nothing
                    }
                });
    }
}
