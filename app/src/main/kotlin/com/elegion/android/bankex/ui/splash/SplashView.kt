package com.elegion.android.bankex.ui.splash

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

internal interface SplashView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openOnBoarding()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openWallet()
}
