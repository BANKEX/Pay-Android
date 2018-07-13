package com.bankex.pay.data.local

import com.bankex.pay.data.model.NetworkInfo


interface OnNetworkChangeListener {
    fun onNetworkChanged(networkInfo: NetworkInfo)
}
