package com.elegion.android.bankex.ui.createwallet

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment

import com.elegion.android.bankex.ui.base.activity.SingleFragmentActivity

class CreateWalletActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = CreateWalletFragment.newInstance()

    companion object {
        fun makeIntent(context: Context) = Intent(context, CreateWalletActivity::class.java)
    }
}
