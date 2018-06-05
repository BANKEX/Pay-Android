package com.elegion.android.bankex.ui.base.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import com.elegion.android.bankex.R
import com.elegion.android.bankex.extension.android.support.v4.app.replaceFragment

abstract class SingleFragmentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.replaceFragment(createFragment()).commit()
        }
    }

    @LayoutRes
    override fun getLayout(): Int {
        return R.layout.ac_single_frame
    }

    abstract fun createFragment(): Fragment
}
