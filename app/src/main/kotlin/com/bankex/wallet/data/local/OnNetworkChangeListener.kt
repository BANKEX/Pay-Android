package com.bankex.wallet.data.local

import com.bankex.wallet.data.model.NetworkInfo


interface OnNetworkChangeListener {
    fun onNetworkChanged(networkInfo: NetworkInfo)
}
