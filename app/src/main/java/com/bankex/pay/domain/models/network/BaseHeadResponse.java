package com.bankex.pay.domain.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @param <T1> тип heads
 * @author Gevork Safaryan on 18.06.2018
 */
public class BaseHeadResponse<T1> {
    @SerializedName("head")
    @Expose
    private T1 head;

    @SerializedName("error")
    @Expose
    private String error;

    public T1 getHead() {
        return head;
    }

    public String getError() {
        return error;
    }
}
