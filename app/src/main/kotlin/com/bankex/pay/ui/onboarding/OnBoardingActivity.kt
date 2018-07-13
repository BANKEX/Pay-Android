package com.bankex.pay.ui.onboarding

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment

import com.bankex.pay.ui.base.activity.SingleFragmentActivity

class OnBoardingActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = OnBoardingFragment.newInstance()

    companion object {
        fun makeIntent(context: Context) = Intent(context, OnBoardingActivity::class.java)
    }
}
