package com.bankex.wallet.data.remote.rest.request

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("scopes")
    private var scopes: List<String>,
    @SerializedName("note")
    private var note: String,
    @SerializedName("client_id")
    private var clientId: String,
    @SerializedName("client_secret")
    private var clientSecret: String
)
