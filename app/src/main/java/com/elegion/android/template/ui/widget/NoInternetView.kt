package com.elegion.android.template.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.Button

import com.elegion.android.template.R
import kotlinx.android.synthetic.main.w_no_internet.view.*

class NoInternetView(context: Context, attrs: AttributeSet) : EmptyView(context, attrs) {
    override fun getLayout(): Int {
        return R.layout.w_no_internet
    }

    override fun getButton(): Button? {
        return noInternetButton
    }
}
