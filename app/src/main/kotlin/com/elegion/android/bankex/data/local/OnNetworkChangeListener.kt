package com.elegion.android.bankex.data.local

import com.elegion.android.bankex.data.model.NetworkInfo


interface OnNetworkChangeListener {
    fun onNetworkChanged(networkInfo: NetworkInfo)
}
