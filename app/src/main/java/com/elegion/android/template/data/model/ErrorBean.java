package com.elegion.android.template.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Max Kuznetsov on 14-Jun-17.
 */
public class ErrorBean {
    @SerializedName("code")
    @Expose
    private String mCode;
    @SerializedName("message")
    @Expose
    private String mMessage;

    public String getCode() {
        return mCode;
    }

    public String getMessage() {
        return mMessage;
    }
}
