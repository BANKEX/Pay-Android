package com.bankex.pay.ui.base.view

import org.reactivestreams.Subscription

interface ErrorStubView {

    fun showErrorStub()

    fun hideErrorStub(s: Subscription)
}
