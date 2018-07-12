package com.bankex.wallet.ui.attention.attentionwarning

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.bankex.wallet.ui.base.activity.SingleFragmentActivity

class AttentionActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = AttentionFragment.newInstance()

    companion object {
        fun makeIntent(context: Context) = Intent(context, AttentionActivity::class.java)
    }
}
