package com.elegion.android.template.ui.features

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.MenuItem

import com.elegion.android.template.R
import com.elegion.android.template.ui.base.activity.SingleFragmentActivity
import com.elegion.android.template.util.ToolbarUtils

class FeaturesActivity : SingleFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ToolbarUtils.setupToolbar(this)
        ToolbarUtils.setHomeEnabled(this)
    }

    @LayoutRes
    override fun getLayout(): Int = R.layout.ac_toolbar_single_frame

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun createFragment(): Fragment = FeaturesFragment.newInstance()

    companion object {
        fun makeIntent(context: Context): Intent {
            return Intent(context, FeaturesActivity::class.java)
        }
    }
}
