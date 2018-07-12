package com.bankex.wallet.ui.base.view

import android.app.Activity
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

import com.bankex.wallet.R

class SnackbarErrorView : ErrorView {

    private var activity: Activity? = null
    private var view: View? = null

    constructor(activity: Activity) {
        this.activity = activity
    }

    constructor(view: View) {
        this.view = view
    }

    override fun showNetworkError() = showSnackBar(R.string.error_network)

    override fun showUnexpectedError() = showSnackBar(R.string.error_unexpected)

    override fun showErrorMessage(message: String) = showSnackBar(message)

    override fun showErrorMessage(@StringRes message: Int) = showSnackBar(message)

    private fun showSnackBar(message: String) {
        showActivitySnackbar(message)
        showFragmentSnackbar(message)
    }

    private fun showActivitySnackbar(message: String) = activity?.let {
        val focusView = it.window.decorView.findFocus()
        if (focusView != null && focusView.context != null) {
            Snackbar.make(focusView, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showFragmentSnackbar(message: String) = view?.let {
        if (it.context != null) {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showSnackBar(@StringRes message: Int) {
        showActivitySnackbar(message)
        showFragmentSnackbar(message)
    }

    private fun showActivitySnackbar(@StringRes message: Int) = activity?.let {
        val focusView = it.window.decorView.findFocus()
        if (focusView != null && focusView.context != null) {
            Snackbar.make(focusView, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showFragmentSnackbar(@StringRes message: Int) = view?.let {
        if (it.context != null) {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun hideErrorMessage() {
        //Not implemented yet
    }
}
