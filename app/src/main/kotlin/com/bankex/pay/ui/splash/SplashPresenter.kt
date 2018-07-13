package com.bankex.pay.ui.splash

import com.arellomobile.mvp.InjectViewState
import com.bankex.pay.data.Repository
import com.bankex.pay.ui.base.presenter.BasePresenter

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
