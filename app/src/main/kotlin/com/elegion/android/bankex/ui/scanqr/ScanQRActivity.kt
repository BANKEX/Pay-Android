package com.elegion.android.bankex.ui.scanqr

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment

import com.elegion.android.bankex.ui.base.activity.SingleFragmentActivity

class ScanQRActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = ScanQRFragment.newInstance()

    companion object {
        fun makeIntent(context: Context) = Intent(context, ScanQRActivity::class.java)
    }
}
