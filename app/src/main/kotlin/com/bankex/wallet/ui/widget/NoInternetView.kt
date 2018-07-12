package com.bankex.wallet.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.Button

import com.bankex.wallet.R
import kotlinx.android.synthetic.main.w_no_internet.view.*

class NoInternetView(context: Context, attrs: AttributeSet) : EmptyView(context, attrs) {
    override fun getLayout(): Int = R.layout.w_no_internet

    override fun getButton(): Button = noInternetButton
}
