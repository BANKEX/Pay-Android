package com.elegion.android.template.data.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author mikhail.barannikov on 04.08.2017
 */

public class LoginRequest {
    @SerializedName("scopes")
    @Expose
    private final List<String> mScopes;

    @SerializedName("note")
    @Expose
    private final String mNote;

    @SerializedName("client_id")
    @Expose
    private final String mClientId;

    @SerializedName("client_secret")
    @Expose
    private final String mClientSecret;

    public LoginRequest(List<String> scopes, String note, String clientId, String clientSecret) {
        mScopes = scopes;
        mNote = note;
        mClientId = clientId;
        mClientSecret = clientSecret;
    }
}
