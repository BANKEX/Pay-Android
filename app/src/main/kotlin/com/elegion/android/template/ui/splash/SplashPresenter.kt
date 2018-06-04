package com.elegion.android.template.ui.splash

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.ui.base.presenter.BasePresenter

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
