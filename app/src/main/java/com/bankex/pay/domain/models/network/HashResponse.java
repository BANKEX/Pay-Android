package com.bankex.pay.domain.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель ответа по хешу
 *
 * @author Gevork Safaryan on 29.05.2018
 */
public class HashResponse {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("block")
    @Expose
    private BigInteger block;
    @SerializedName("addrfrom")
    @Expose
    private String addrfrom;
    @SerializedName("addrto")
    @Expose
    private String addrto;
    @SerializedName("isotime")
    @Expose
    private String isotime;
    @SerializedName("type")
    @Expose
    private TransactionType type;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("iscontract")
    @Expose
    private int iscontract;
    @SerializedName("isinner")
    @Expose
    private int isinner;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("token")
    @Expose
    private Token token;
    @SerializedName("txfee")
    @Expose
    private String txfee;
    @SerializedName("gasused")
    @Expose
    private BigInteger gasused;
    @SerializedName("gascost")
    @Expose
    private BigInteger gascost;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("rcplogs")
    @Expose
    private List<Rcplog> rcplogs = new ArrayList<Rcplog>();

    public String getId() {
        return id;
    }

    public String getHash() {
        return hash;
    }

    public BigInteger getBlock() {
        return block;
    }


    public String getAddrfrom() {
        return addrfrom;
    }


    public String getAddrto() {
        return addrto;
    }


    public String getIsotime() {
        return isotime;
    }


    public TransactionType getType() {
        return type;
    }


    public int getStatus() {
        return status;
    }


    public String getError() {
        return error;
    }


    public int getIscontract() {
        return iscontract;
    }


    public int getIsinner() {
        return isinner;
    }


    public String getValue() {
        return value;
    }


    public Token getToken() {
        return token;
    }


    public String getTxfee() {
        return txfee;
    }


    public BigInteger getGasused() {
        return gasused;
    }


    public BigInteger getGascost() {
        return gascost;
    }


    public String getData() {
        return data;
    }

    public List<Rcplog> getRcplogs() {
        return rcplogs;
    }
}



