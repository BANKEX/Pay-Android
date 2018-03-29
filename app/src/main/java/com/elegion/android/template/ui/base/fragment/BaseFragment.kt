package com.elegion.android.template.ui.base.fragment

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.elegion.android.template.ui.base.view.ErrorView
import com.elegion.android.template.ui.base.view.LoadingView
import com.elegion.android.template.ui.base.view.ToastErrorView
import com.elegion.android.template.ui.dialog.LoadingDialog

abstract class BaseFragment : MvpAppCompatFragment(), LoadingView, ErrorView {
    protected var mIsAfterOnSavedState: Boolean = false

    private var mRunOnResume: Runnable? = null
    protected lateinit var mLoadingView: LoadingView
    protected lateinit var mErrorView: ErrorView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mIsAfterOnSavedState = false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mLoadingView = createLoadingView()
        mErrorView = createErrorView()
    }

    override fun onResume() {
        super.onResume()
        if (mRunOnResume != null) {
            mRunOnResume!!.run()
            mRunOnResume = null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mIsAfterOnSavedState = true
    }

    fun postOnResume(run: Runnable?) {
        if (mIsAfterOnSavedState) {
            mRunOnResume = run
        } else {
            run?.run()
        }
    }

    protected fun createLoadingView(): LoadingView = LoadingDialog.view(childFragmentManager)

    protected fun createErrorView(): ErrorView = ToastErrorView(activity!!)

    @LayoutRes
    protected abstract fun getLayout(): Int

    override fun showLoadingIndicator() = mLoadingView.showLoadingIndicator()

    override fun hideLoadingIndicator() = mLoadingView.hideLoadingIndicator()

    override fun showNetworkError() = mErrorView.showNetworkError()

    override fun showUnexpectedError() = mErrorView.showUnexpectedError()

    override fun showErrorMessage(message: String) = mErrorView.showErrorMessage(message)

    override fun showErrorMessage(@StringRes message: Int) = mErrorView.showErrorMessage(message)

    override fun hideErrorMessage() = mErrorView.hideErrorMessage()
}
