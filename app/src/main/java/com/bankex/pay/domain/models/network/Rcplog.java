package com.bankex.pay.domain.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель ответа rcpLog
 *
 * @author Gevork Safaryan on 29.05.2018
 */
public class Rcplog {

    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("topics")
    @Expose
    private List<String> topics = new ArrayList<String>();
    @SerializedName("data")
    @Expose
    private String data;

    public String getAddr() {
        return addr;
    }


    public List<String> getTopics() {
        return topics;
    }


    public String getData() {
        return data;
    }


}
