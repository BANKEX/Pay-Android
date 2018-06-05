package com.elegion.android.bankex.ui.splash

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.bankex.data.Repository
import com.elegion.android.bankex.ui.base.presenter.BasePresenter

@InjectViewState
internal class SplashPresenter(private val repository: Repository) : BasePresenter<SplashView>() {

    fun timerFinish() {
        val onBoardingFlag = repository.onBoardingFlag
        if (onBoardingFlag) {
            viewState.openOnBoarding()
        } else {
            viewState.openWallet()
        }
    }
}
