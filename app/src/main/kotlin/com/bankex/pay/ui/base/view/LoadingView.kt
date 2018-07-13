package com.bankex.pay.ui.base.view

import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.bankex.pay.util.moxy.strategy.AddToEndSingleByTagStrategy
import com.bankex.pay.util.moxy.strategy.OneExecutionByTagStrategy

interface LoadingView {
    @StateStrategyType(value = AddToEndSingleByTagStrategy::class, tag = "LoadingView")
    fun showLoadingIndicator()

    @StateStrategyType(value = OneExecutionByTagStrategy::class, tag = "LoadingView")
    fun hideLoadingIndicator()
}
