package com.bankex.wallet.ui.base.view

import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.bankex.wallet.util.moxy.strategy.AddToEndSingleByTagStrategy
import com.bankex.wallet.util.moxy.strategy.OneExecutionByTagStrategy

interface LoadingView {
    @StateStrategyType(value = AddToEndSingleByTagStrategy::class, tag = "LoadingView")
    fun showLoadingIndicator()

    @StateStrategyType(value = OneExecutionByTagStrategy::class, tag = "LoadingView")
    fun hideLoadingIndicator()
}
