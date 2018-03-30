package com.elegion.android.template.ui.base.view

import org.reactivestreams.Subscription

interface ErrorStubView {

    fun showErrorStub()

    fun hideErrorStub(s: Subscription)
}
