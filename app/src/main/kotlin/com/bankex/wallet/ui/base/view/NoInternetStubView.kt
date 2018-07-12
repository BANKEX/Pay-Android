package com.bankex.wallet.ui.base.view

import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.bankex.wallet.util.moxy.strategy.AddToEndSingleByTagStrategy
import com.bankex.wallet.util.moxy.strategy.SkipByTagStrategy

interface NoInternetStubView {
    @StateStrategyType(value = AddToEndSingleByTagStrategy::class, tag = "NoInternetStubView")
    fun showNoInternetStub()

    @StateStrategyType(value = SkipByTagStrategy::class, tag = "NoInternetStubView")
    fun hideNoInternetStub()
}
