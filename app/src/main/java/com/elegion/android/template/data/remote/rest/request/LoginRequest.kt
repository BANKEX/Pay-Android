package com.elegion.android.template.data.remote.rest.request

import com.google.gson.annotations.SerializedName

class LoginRequest(
        @SerializedName("scopes")
        private var mScopes: List<String>,
        @SerializedName("note")
        private var mNote: String,
        @SerializedName("client_id")
        private var mClientId: String,
        @SerializedName("client_secret")
        private var mClientSecret: String
)
