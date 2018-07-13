package com.bankex.pay.data.model

import com.google.gson.annotations.SerializedName

class Ticker {
    var id: String? = null
    var name: String? = null
    var symbol: String? = null
    var price: String? = null
    @SerializedName("percent_change_24h")
    var percentChange24h: String? = null
}
