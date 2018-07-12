package com.bankex.wallet.ui.base.view

import org.reactivestreams.Subscription

interface ErrorStubView {

    fun showErrorStub()

    fun hideErrorStub(s: Subscription)
}
