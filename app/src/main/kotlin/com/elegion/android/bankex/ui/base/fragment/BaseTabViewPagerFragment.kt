package com.elegion.android.bankex.ui.base.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elegion.android.bankex.R
import kotlinx.android.synthetic.main.fr_toolbar_tab_viewpager.*

abstract class BaseTabViewPagerFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun getLayout(): Int = R.layout.fr_toolbar_tab_viewpager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewPager.adapter = getAdapter()
        tabLayout?.setupWithViewPager(viewPager)
    }

    internal abstract fun getAdapter(): PagerAdapter

    fun getTabLayout(): TabLayout? = tabLayout

    fun getViewPager(): ViewPager? = viewPager
}
