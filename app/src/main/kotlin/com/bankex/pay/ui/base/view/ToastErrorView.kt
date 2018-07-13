package com.bankex.pay.ui.base.view

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

import com.bankex.pay.R

class ToastErrorView(private val context: Context) : ErrorView {

    override fun showNetworkError() = showToast(R.string.error_network)

    override fun showUnexpectedError() = showToast(R.string.error_unexpected)

    override fun showErrorMessage(message: String) = showToast(message)

    override fun showErrorMessage(@StringRes message: Int) = showToast(message)

    private fun showToast(message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    private fun showToast(@StringRes message: Int) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    override fun hideErrorMessage() {
        //Not implemented yet
    }
}
