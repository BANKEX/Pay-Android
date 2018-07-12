package com.bankex.wallet.ui.attention.attentioncopy

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Denis Anisimov.
 */
interface AttentionViewCopy : MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun copyPathPhraseToClipBoard()

    @StateStrategyType(AddToEndStrategy::class)
    fun showFinishButton()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun finish()
}