package com.elegion.android.bankex.ui.base.fragment

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.elegion.android.bankex.ui.base.view.ErrorView
import com.elegion.android.bankex.ui.base.view.LoadingView
import com.elegion.android.bankex.ui.base.view.ToastErrorView
import com.elegion.android.bankex.ui.dialog.LoadingDialog

abstract class BaseFragment : MvpAppCompatFragment(), LoadingView, ErrorView {
    protected var isAfterOnSavedState: Boolean = false

    private var runOnResume: Runnable? = null
    protected lateinit var loadingView: LoadingView
    protected lateinit var errorView: ErrorView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isAfterOnSavedState = false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadingView = createLoadingView()
        errorView = createErrorView()
    }

    override fun onResume() {
        super.onResume()
        if (runOnResume != null) {
            runOnResume!!.run()
            runOnResume = null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isAfterOnSavedState = true
    }

    fun postOnResume(run: Runnable?) {
        if (isAfterOnSavedState) {
            runOnResume = run
        } else {
            run?.run()
        }
    }

    protected fun createLoadingView(): LoadingView = LoadingDialog.view(childFragmentManager)

    protected fun createErrorView(): ErrorView = ToastErrorView(activity!!)

    @LayoutRes
    protected abstract fun getLayout(): Int

    override fun showLoadingIndicator() = loadingView.showLoadingIndicator()

    override fun hideLoadingIndicator() = loadingView.hideLoadingIndicator()

    override fun showNetworkError() = errorView.showNetworkError()

    override fun showUnexpectedError() = errorView.showUnexpectedError()

    override fun showErrorMessage(message: String) = errorView.showErrorMessage(message)

    override fun showErrorMessage(@StringRes message: Int) = errorView.showErrorMessage(message)

    override fun hideErrorMessage() = errorView.hideErrorMessage()
}
