package com.bankex.pay.data.model

class NetworkInfo(
        val name: String,
        val symbol: String,
        val rpcServerUrl: String,
        val backendUrl: String,
        val etherscanUrl: String,
        val chainId: Int,
        val isMainNetwork: Boolean)
