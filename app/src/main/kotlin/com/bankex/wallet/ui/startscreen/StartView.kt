package com.bankex.wallet.ui.startscreen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

internal interface StartView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun importWallet()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun createWallet()
}
