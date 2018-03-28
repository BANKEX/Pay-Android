package com.elegion.android.template.ui.splash

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

internal interface SplashView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openLogin()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFeatures()
}
