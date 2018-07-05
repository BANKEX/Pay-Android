package com.elegion.android.bankex.ui.attention.attentioncopy

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.elegion.android.bankex.ui.base.activity.SingleFragmentActivity

class AttentionCopyActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = AttentionFragmentCopy.newInstance()

    companion object {
        fun makeIntent(context: Context) = Intent(context, AttentionCopyActivity::class.java)
    }
}
