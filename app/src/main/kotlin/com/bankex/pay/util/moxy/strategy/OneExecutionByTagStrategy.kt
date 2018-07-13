package com.bankex.pay.util.moxy.strategy

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.ViewCommand
import com.arellomobile.mvp.viewstate.strategy.StateStrategy

/**
 * Command will be saved in commands queue. And this command will be removed after first execution.
 * If commands queue contains commands with same tag, then existing commands will be removed.
 */
class OneExecutionByTagStrategy : StateStrategy {
    override fun <T : MvpView> beforeApply(currentState: MutableList<ViewCommand<T>>, incomingCommand: ViewCommand<T>) {
        val iterator = currentState.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.tag == incomingCommand.tag) {
                iterator.remove()
                break
            }
        }

        currentState.add(incomingCommand)
    }

    override fun <T : MvpView> afterApply(currentState: MutableList<ViewCommand<T>>, incomingCommand: ViewCommand<T>) {
        currentState.remove(incomingCommand)
    }
}
