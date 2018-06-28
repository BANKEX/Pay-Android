package com.elegion.android.bankex.ui.attention

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.elegion.android.bankex.R
import com.elegion.android.bankex.ui.base.activity.SingleFragmentActivity
import com.elegion.android.bankex.ui.onboarding.OnBoardingActivity
import com.elegion.android.bankex.ui.onboarding.OnBoardingFragment

class AttentionActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment = AttentionFragment.newInstance()

    companion object {
        fun makeIntent(context: Context) = Intent(context, AttentionActivity::class.java)
    }
}
