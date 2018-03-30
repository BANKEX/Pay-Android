package com.elegion.android.template.ui.base.view

import android.support.annotation.StringRes

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface ErrorView {

    fun showNetworkError()

    fun showUnexpectedError()

    fun showErrorMessage(message: String)

    fun showErrorMessage(@StringRes message: Int)

    fun hideErrorMessage()
}
