package com.elegion.android.bankex.ui.attention.attentionwarning

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Denis Anisimov.
 */
interface AttentionView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun goToCopyPathPhrase()
}