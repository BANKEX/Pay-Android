package com.bankex.pay.data.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bankex.pay.C
import com.bankex.pay.data.model.GasSettings
import java.math.BigInteger

class PreferencesDataSource(val context: Context) {
    private val CURRENT_ACCOUNT_ADDRESS_KEY = "current_account_address"
    private val DEFAULT_NETWORK_NAME_KEY = "default_network_name"
    private val GAS_PRICE_KEY = "gas_price"
    private val GAS_LIMIT_KEY = "gas_limit"
    private val GAS_LIMIT_FOR_TOKENS_KEY = "gas_limit_for_tokens"

    private var preferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    var onBoardingFlag: Boolean
        get() = preferences.getBoolean(KEY_ONBOARDING_FLAG, false)
        set(onboardingFlag) = editor.putBoolean(KEY_ONBOARDING_FLAG, onboardingFlag).apply()

    private val editor: SharedPreferences.Editor
        @SuppressLint("CommitPrefEdits")
        get() = preferences.edit()

    companion object {
        private const val KEY_ONBOARDING_FLAG = "KEY_ONBOARDING_FLAG"
    }

    fun getCurrentWalletAddress(): String? {
        return preferences.getString(CURRENT_ACCOUNT_ADDRESS_KEY, null)
    }

    fun setCurrentWalletAddress(address: String) {
        preferences.edit().putString(CURRENT_ACCOUNT_ADDRESS_KEY, address).apply()
    }

    fun getDefaultNetwork(): String? {
        return preferences.getString(DEFAULT_NETWORK_NAME_KEY, null)
    }

    fun setDefaultNetwork(netName: String) {
        preferences.edit().putString(DEFAULT_NETWORK_NAME_KEY, netName).apply()
    }

    fun getGasSettings(forTokenTransfer: Boolean): GasSettings {
        val gasPrice = BigInteger(preferences.getString(GAS_PRICE_KEY, C.DEFAULT_GAS_PRICE)!!)
        var gasLimit = BigInteger(preferences.getString(GAS_LIMIT_KEY, C.DEFAULT_GAS_LIMIT)!!)
        if (forTokenTransfer) {
            gasLimit = BigInteger(preferences.getString(GAS_LIMIT_FOR_TOKENS_KEY, C.DEFAULT_GAS_LIMIT_FOR_TOKENS)!!)
        }

        return GasSettings(gasPrice, gasLimit)
    }

    fun setGasSettings(gasSettings: GasSettings) {
        preferences.edit().putString(GAS_PRICE_KEY, gasSettings.gasPrice.toString()).apply()
        preferences.edit().putString(GAS_PRICE_KEY, gasSettings.gasLimit.toString()).apply()
    }
}