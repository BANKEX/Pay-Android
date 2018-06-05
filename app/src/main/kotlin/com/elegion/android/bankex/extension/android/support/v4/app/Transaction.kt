package com.elegion.android.bankex.extension.android.support.v4.app

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.elegion.android.bankex.R

class Transaction(val fm: FragmentManager, val fragment: Fragment, val type: Type) {
    var addToBackStack: Boolean = false
    var enter: Int = 0
    var exit: Int = 0
    var popEnter: Int = 0
    var popExit: Int = 0

    fun addToBackStack(addToBackStack: Boolean): Transaction {
        this.addToBackStack = addToBackStack
        return this
    }

    fun enter(enter: Int): Transaction {
        this.enter = enter
        return this
    }

    fun exit(exit: Int): Transaction {
        this.exit = exit
        return this
    }

    fun popEnter(popEnter: Int): Transaction {
        this.popEnter = popEnter
        return this
    }

    fun popExit(popExit: Int): Transaction {
        this.popExit = popExit
        return this
    }

    fun commit() {
        val tr = fm.beginTransaction()
        if (enter != 0 && exit != 0) {
            if (popEnter != 0 && popExit != 0) {
                tr.setCustomAnimations(enter, exit, popEnter, popExit)
            } else {
                tr.setCustomAnimations(enter, exit)
            }
        }

        if (type == Type.REPLACE) {
            tr.add(R.id.flContainer, fragment, fragment.javaClass.name)
        } else {
            tr.replace(R.id.flContainer, fragment, fragment.javaClass.name)
        }

        if (addToBackStack) {
            tr.addToBackStack(fragment.javaClass.name)
        }
        tr.commit()
    }

    enum class Type {
        ADD, REPLACE
    }
}