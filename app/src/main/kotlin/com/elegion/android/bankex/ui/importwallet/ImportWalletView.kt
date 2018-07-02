package com.elegion.android.bankex.ui.importwallet

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ImportWalletView : MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun showButtonEnabled(enabled: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showScanQR()
}