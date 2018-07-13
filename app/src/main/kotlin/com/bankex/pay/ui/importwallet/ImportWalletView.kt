package com.bankex.pay.ui.importwallet

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ImportWalletView : MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun showButtonEnabled(enabled: Boolean)

    @StateStrategyType(AddToEndStrategy::class)
    fun pasteScannedAddress(address: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showScanQR()
}