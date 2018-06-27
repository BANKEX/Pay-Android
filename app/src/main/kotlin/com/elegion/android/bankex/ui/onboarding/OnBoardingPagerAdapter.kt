package com.elegion.android.bankex.ui.onboarding

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class OnBoardingPagerAdapter(val supportFragmentManager: FragmentManager?, val
list: List<Onboarding>) : FragmentPagerAdapter(supportFragmentManager) {

    override fun getItem(position: Int): Fragment {
        val get = list.get(position)
        val fragment = ContentOnBoardingFragment.newInstance(get.id, get.title, get.desc)
        return fragment
    }

    override fun getCount(): Int {
        return 3
    }
}
