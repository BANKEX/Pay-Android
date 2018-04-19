package com.elegion.android.template.ui.login

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment

import com.elegion.android.template.ui.base.activity.SingleFragmentActivity

class LoginActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment = LoginFragment.newInstance()

    companion object {
        fun makeIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
