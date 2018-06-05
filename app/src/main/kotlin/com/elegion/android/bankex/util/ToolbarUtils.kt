package com.elegion.android.bankex.util

import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.elegion.android.bankex.R

object ToolbarUtils {

    @JvmStatic
    fun setupToolbar(activity: AppCompatActivity) {
        val toolbar = activity.findViewById<View>(R.id.toolbar) as? Toolbar
        activity.setSupportActionBar(toolbar)
    }

    @JvmStatic
    fun setupToolbar(activity: AppCompatActivity, title: String) {
        val toolbar = activity.findViewById<View>(R.id.toolbar) as? Toolbar
        toolbar?.let {
            it.title = title
            activity.setSupportActionBar(toolbar)
        }
    }

    @JvmStatic
    fun setToolbarTitle(activity: AppCompatActivity, @StringRes title: Int) = activity.supportActionBar?.setTitle(title)

    @JvmStatic
    fun setToolbarTitle(activity: AppCompatActivity, title: CharSequence) {
        activity.supportActionBar?.title = title
    }

    @JvmStatic
    fun setToolbarSubtitle(activity: AppCompatActivity, @StringRes subtitle: Int) {
        val toolbar = activity.findViewById<View>(R.id.toolbar) as? Toolbar
        // hack to render toolbar after subtitle is set if called in onActivityCreated
        toolbar?.post { activity.supportActionBar?.setSubtitle(subtitle) }
    }

    @JvmStatic
    fun setToolbarSubtitle(activity: AppCompatActivity, subtitle: String) {
        val toolbar = activity.findViewById<View>(R.id.toolbar) as? Toolbar
        // hack to render toolbar after subtitle is set if called in onActivityCreated
        toolbar?.post { activity.supportActionBar?.subtitle = subtitle }
    }

    @JvmStatic
    fun setHomeEnabled(activity: AppCompatActivity) = activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

    @JvmStatic
    fun setHomeDisabled(activity: AppCompatActivity) = activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
}
