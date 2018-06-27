package com.elegion.android.bankex.ui.importwallet

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.elegion.android.bankex.ui.base.activity.SingleFragmentActivity
import com.elegion.android.bankex.ui.createwallet.CreateWalletFragment

class ImportWalletActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = CreateWalletFragment.newInstance()

    companion object {
        fun makeIntent(context: Context) = Intent(context, ImportWalletActivity::class.java)
    }
}
