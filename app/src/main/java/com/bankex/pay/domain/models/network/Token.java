package com.bankex.pay.domain.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Модель Токена
 *
 * @author Gevork Safaryan on 29.05.2018
 */
public class Token {

    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("smbl")
    @Expose
    private String smbl;
    @SerializedName("dcm")
    @Expose
    private int dcm;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("dynamic")
    @Expose
    private int dynamic;

    public String getAddr() {
        return addr;
    }

    public String getName() {
        return name;
    }

    public String getSmbl() {
        return smbl;
    }

    public int getDcm() {
        return dcm;
    }

    public int getType() {
        return type;
    }

    public String getBalance() {
        return balance;
    }

    public String getIcon() {
        return icon;
    }

    public int getDynamic() {
        return dynamic;
    }
}
