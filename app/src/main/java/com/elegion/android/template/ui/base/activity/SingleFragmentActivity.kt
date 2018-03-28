package com.elegion.android.template.ui.base.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment

import com.elegion.android.template.R
import com.elegion.android.template.util.FragmentUtils

abstract class SingleFragmentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(supportFragmentManager, createFragment())
        }
    }

    @LayoutRes
    override fun getLayout(): Int {
        return R.layout.ac_single_frame
    }

    abstract fun createFragment(): Fragment
}
