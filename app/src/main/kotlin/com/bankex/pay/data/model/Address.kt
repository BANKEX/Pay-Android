package com.bankex.pay.data.model

import android.text.TextUtils
import java.util.regex.Pattern

/**
 * @author Denis Anisimov.
 */

class Address {
    private val ignoreCaseAddrPattern = Pattern.compile("(?i)^(0x)?[0-9a-f]{40}$")
    private val lowerCaseAddrPattern = Pattern.compile("^(0x)?[0-9a-f]{40}$")
    private val upperCaseAddrPattern = Pattern.compile("^(0x)?[0-9A-F]{40}$")

    val value: String

    constructor(address: String) {
        this.value = address
    }

    fun isAddress(address: String): Boolean {
        return !(TextUtils.isEmpty(address) || !ignoreCaseAddrPattern.matcher(address).find()) && (lowerCaseAddrPattern.matcher(address).find() || upperCaseAddrPattern.matcher(address).find())
    }
}