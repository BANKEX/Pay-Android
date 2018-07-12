package com.bankex.wallet.ui.splash

import com.arellomobile.mvp.InjectViewState
import com.bankex.wallet.data.Repository
import com.bankex.wallet.ui.base.presenter.BasePresenter

@InjectViewState
internal class SplashPresenter(private val repository: Repository) : BasePresenter<SplashView>() {

    fun timerFinish() {
        val onBoardingFlag = repository.onBoardingFlag
        if (onBoardingFlag) {
            viewState.openWallet()
        } else {
            viewState.openOnBoarding()
        }
    }
}
