package com.bankex.pay.data.local


import android.text.TextUtils
import com.bankex.pay.C.CLASSIC_NETWORK_NAME
import com.bankex.pay.C.ETC_SYMBOL
import com.bankex.pay.C.ETHEREUM_NETWORK_NAME
import com.bankex.pay.C.ETH_SYMBOL
import com.bankex.pay.C.KOVAN_NETWORK_NAME
import com.bankex.pay.C.POA_NETWORK_NAME
import com.bankex.pay.C.POA_SYMBOL
import com.bankex.pay.C.ROPSTEN_NETWORK_NAME
import com.bankex.pay.data.model.NetworkInfo
import java.util.*


class EthereumNetworkDataSource(private val preferences: PreferencesDataSource) {

    val availableNetworkList = arrayOf(
            NetworkInfo(ETHEREUM_NETWORK_NAME, ETH_SYMBOL,
                    "https://mainnet.infura.io/llyrtzQ3YhkdESt2Fzrk",
                    "https://api.trustwalletapp.com/",
                    "https://etherscan.io/", 1, true),
            NetworkInfo(CLASSIC_NETWORK_NAME, ETC_SYMBOL,
                    "https://mewapi.epool.io/",
                    "https://classic.trustwalletapp.com",
                    "https://gastracker.io", 61, true),
            NetworkInfo(POA_NETWORK_NAME, POA_SYMBOL,
                    "https://core.poa.network",
                    "https://poa.trustwalletapp.com", "poa", 99, false),
            NetworkInfo(KOVAN_NETWORK_NAME, ETH_SYMBOL,
                    "https://kovan.infura.io/llyrtzQ3YhkdESt2Fzrk",
                    "https://kovan.trustwalletapp.com/",
                    "https://kovan.etherscan.io", 42, false),
            NetworkInfo(ROPSTEN_NETWORK_NAME, ETH_SYMBOL,
                    "https://ropsten.infura.io/llyrtzQ3YhkdESt2Fzrk",
                    "https://ropsten.trustwalletapp.com/",
                    "https://ropsten.etherscan.io", 3, false))

    var defaultNetwork: NetworkInfo? = null
        private set
    private val onNetworkChangedListeners = HashSet<OnNetworkChangeListener>()

    init {
        defaultNetwork = getByName(preferences.getDefaultNetwork())
        if (defaultNetwork == null) {
            defaultNetwork = availableNetworkList[0]
        }
    }

    private fun getByName(name: String?): NetworkInfo? {
        if (!TextUtils.isEmpty(name)) {
            for (NETWORK in availableNetworkList) {
                if (name == NETWORK.name) {
                    return NETWORK
                }
            }
        }
        return null
    }

    fun setDefaultNetworkInfo(networkInfo: NetworkInfo) {
        defaultNetwork = networkInfo
        preferences.setDefaultNetwork(defaultNetwork!!.name)

        for (listener in onNetworkChangedListeners) {
            listener.onNetworkChanged(networkInfo)
        }
    }

    fun addOnChangeDefaultNetwork(onNetworkChanged: OnNetworkChangeListener) {
        onNetworkChangedListeners.add(onNetworkChanged)
    }
}
