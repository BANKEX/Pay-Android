package com.elegion.android.template.data.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author mikhail.barannikov on 04.08.2017
 */
public class LoginResponse {
    @SerializedName("id")
    @Expose
    private int mId;

    @SerializedName("url")
    @Expose
    private String mUrl;

    @SerializedName("app")
    @Expose
    private App mApp;

    @SerializedName("token")
    @Expose
    private String mToken;

    @SerializedName("hashed_token")
    @Expose
    private String mHashedToken;

    @SerializedName("token_last_eight")
    @Expose
    private String mTokenLastEight;

    @SerializedName("note")
    @Expose
    private String mNote;

    @SerializedName("note_url")
    @Expose
    private String mNoteUrl;

    @SerializedName("created_at")
    @Expose
    private String mCreatedAt;

    @SerializedName("updated_at")
    @Expose
    private String mUpdatedAt;

    @SerializedName("scopes")
    @Expose
    private List<String> mScopes;

    @SerializedName("fingerprint")
    @Expose
    private String mFingerprintUrl;

    public String getToken() {
        return mToken;
    }

    public LoginResponse(String token) {
        mToken = token;
    }

    private static class App {
        @SerializedName("name")
        @Expose
        private String mName;

        @SerializedName("url")
        @Expose
        private String mUrl;

        @SerializedName("client_id")
        @Expose
        private String mClientId;
    }
}
