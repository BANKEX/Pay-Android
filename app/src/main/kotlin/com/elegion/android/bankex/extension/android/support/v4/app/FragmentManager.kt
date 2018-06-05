package com.elegion.android.bankex.extension.android.support.v4.app

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

fun FragmentManager.addFragment(fragment: Fragment): Transaction {
    return Transaction(this, fragment, Transaction.Type.ADD)
}

fun FragmentManager.replaceFragment(fragment: Fragment): Transaction {
    return Transaction(this, fragment, Transaction.Type.REPLACE)
}
