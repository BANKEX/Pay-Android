package com.elegion.android.bankex.ui.base.view

import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.elegion.android.bankex.util.moxy.strategy.AddToEndSingleByTagStrategy
import com.elegion.android.bankex.util.moxy.strategy.SkipByTagStrategy

interface NoInternetStubView {
    @StateStrategyType(value = AddToEndSingleByTagStrategy::class, tag = "NoInternetStubView")
    fun showNoInternetStub()

    @StateStrategyType(value = SkipByTagStrategy::class, tag = "NoInternetStubView")
    fun hideNoInternetStub()
}
