package com.elegion.android.bankex.util.moxy.strategy

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.ViewCommand
import com.arellomobile.mvp.viewstate.strategy.StateStrategy

/**
 * Command will not be put in commands queue. If commands queue contains commands with same tag, then existing commands will be removed.
 */
class SkipByTagStrategy : StateStrategy {
    override fun <T : MvpView> beforeApply(currentState: MutableList<ViewCommand<T>>, incomingCommand: ViewCommand<T>) {
        val iterator = currentState.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.tag == incomingCommand.tag) {
                iterator.remove()
                break
            }
        }
    }

    override fun <T : MvpView> afterApply(currentState: List<ViewCommand<T>>, incomingCommand: ViewCommand<T>) {
        // pass
    }
}
