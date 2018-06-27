package com.elegion.android.bankex.ui.onboarding

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.elegion.android.bankex.ui.base.view.ErrorView
import com.elegion.android.bankex.ui.base.view.LoadingView
import com.elegion.android.bankex.ui.base.view.NoInternetStubView

internal interface OnBoardingView : LoadingView, NoInternetStubView, ErrorView, MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onBoardingConfirmed()

    @StateStrategyType(AddToEndStrategy::class)
    fun onBoardingSetLabelStart()

    @StateStrategyType(AddToEndStrategy::class)
    fun onBoardingNext(currentItem: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun onBoardingSkipped()
}
