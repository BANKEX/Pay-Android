package com.bankex.wallet.ui.base.view

import android.content.res.Resources
import android.support.annotation.StringRes
import android.support.v4.app.FragmentManager

import com.bankex.wallet.R
import com.bankex.wallet.ui.dialog.MessageDialog

class DialogErrorView(private val fragmentManager: FragmentManager?, private val resources: Resources) : ErrorView {

    override fun showNetworkError() = showDialog(R.string.error_network)

    override fun showUnexpectedError() = showDialog(R.string.error_unexpected)

    override fun showErrorMessage(message: String) = showDialog(message)

    override fun showErrorMessage(@StringRes message: Int) = showDialog(message)

    override fun hideErrorMessage() {
        //Not implemented yet
    }

    private fun showDialog(message: String) {
        fragmentManager?.let {
            MessageDialog.show(fragmentManager, MessageDialog.Params(null, message, resources.getString(R.string.btn_ok), null))
        }
    }

    private fun showDialog(@StringRes message: Int) = showDialog(resources.getString(message))
}
