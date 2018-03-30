package com.elegion.android.template.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.elegion.android.template.R

object FragmentUtils {

    @JvmOverloads
    @JvmStatic
    fun replaceFragment(fm: FragmentManager, fragment: Fragment, addToBackStack: Boolean = false,
                        enter: Int = 0, exit: Int = 0, popEnter: Int = 0, popExit: Int = 0) {
        val tr = fm.beginTransaction()
        if (enter != 0 && exit != 0) {
            if (popEnter != 0 && popExit != 0) {
                tr.setCustomAnimations(enter, exit, popEnter, popExit)
            } else {
                tr.setCustomAnimations(enter, exit)
            }
        }
        tr.replace(R.id.flContainer, fragment, fragment.javaClass.name)
        if (addToBackStack) {
            tr.addToBackStack(fragment.javaClass.name)
        }
        tr.commit()
    }

    @JvmOverloads
    @JvmStatic
    fun addFragment(fm: FragmentManager, fragment: Fragment, addToBackStack: Boolean = false,
                    enter: Int = 0, exit: Int = 0, popEnter: Int = 0, popExit: Int = 0) {
        val tr = fm.beginTransaction()
        if (enter != 0 && exit != 0) {
            if (popEnter != 0 && popExit != 0) {
                tr.setCustomAnimations(enter, exit, popEnter, popExit)
            } else {
                tr.setCustomAnimations(enter, exit)
            }
        }
        tr.add(R.id.flContainer, fragment, fragment.javaClass.name)
        if (addToBackStack) {
            tr.addToBackStack(fragment.javaClass.name)
        }
        tr.commit()
    }
}
