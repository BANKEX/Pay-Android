package com.bankex.wallet.ui.importwallet

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.bankex.wallet.ui.base.activity.SingleFragmentActivity
import com.bankex.wallet.ui.createwallet.CreateWalletFragment

class ImportWalletActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = ImportWalletFragment.newInstance()

    companion object {
        fun makeIntent(context: Context) = Intent(context, ImportWalletActivity::class.java)
    }
}
