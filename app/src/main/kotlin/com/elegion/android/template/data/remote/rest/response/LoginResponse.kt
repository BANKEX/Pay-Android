package com.elegion.android.template.data.remote.rest.response

import com.google.gson.annotations.SerializedName

class LoginResponse(
        @SerializedName("token")
        var token: String
) {
    @SerializedName("id")
    private var id: Int = 0

    @SerializedName("url")
    private var url: String? = null

    @SerializedName("app")
    private var app: App? = null

    @SerializedName("hashed_token")
    private var hashedToken: String? = null

    @SerializedName("token_last_eight")
    private var tokenLastEight: String? = null

    @SerializedName("note")
    private var note: String? = null

    @SerializedName("note_url")
    private var noteUrl: String? = null

    @SerializedName("created_at")
    private var createdAt: String? = null

    @SerializedName("updated_at")
    private var updatedAt: String? = null

    @SerializedName("scopes")
    private var scopes: List<String>? = null

    @SerializedName("fingerprint")
    private var fingerprintUrl: String? = null

    private class App {
        @SerializedName("name")
        private var name: String? = null

        @SerializedName("url")
        private var url: String? = null

        @SerializedName("client_id")
        private var clientId: String? = null
    }
}
