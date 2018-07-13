package com.bankex.pay.ui.createwallet

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface CreateWalletView : MvpView {

    fun showGeneratedWallet(walletAddress: String)

    @StateStrategyType(AddToEndStrategy::class)
    fun showButtonEnabled(enabled: Boolean)
}
