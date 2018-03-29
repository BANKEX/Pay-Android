package com.elegion.android.template.data.remote.rest.response

import com.google.gson.annotations.SerializedName

class LoginResponse(
        @SerializedName("token")
        var token: String
) {
    @SerializedName("id")
    private var mId: Int = 0

    @SerializedName("url")
    private var mUrl: String? = null

    @SerializedName("app")
    private var mApp: App? = null

    @SerializedName("hashed_token")
    private var mHashedToken: String? = null

    @SerializedName("token_last_eight")
    private var mTokenLastEight: String? = null

    @SerializedName("note")
    private var mNote: String? = null

    @SerializedName("note_url")
    private var mNoteUrl: String? = null

    @SerializedName("created_at")
    private var mCreatedAt: String? = null

    @SerializedName("updated_at")
    private var mUpdatedAt: String? = null

    @SerializedName("scopes")
    private var mScopes: List<String>? = null

    @SerializedName("fingerprint")
    private var mFingerprintUrl: String? = null

    private class App {
        @SerializedName("name")
        private var mName: String? = null

        @SerializedName("url")
        private var mUrl: String? = null

        @SerializedName("client_id")
        private var mClientId: String? = null
    }
}
