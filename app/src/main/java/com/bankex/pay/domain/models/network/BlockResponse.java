package com.bankex.pay.domain.models.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

/**
 * @author Gevork Safaryan on 18.06.2018
 */
public class BlockResponse {

    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("block")
    @Expose
    private BigInteger block;
    @SerializedName("mainTxCount")
    @Expose
    private int maintxcount;
    @SerializedName("innerTxCount")
    @Expose
    private int innertxcount;
    @SerializedName("tokenTxCount")
    @Expose
    private int tokentxcount;
    @SerializedName("totaltxcount")
    @Expose
    private int totaltxcount;
    @SerializedName("gasLimit")
    @Expose
    private BigInteger gaslimit;
    @SerializedName("gasUsed")
    @Expose
    private BigInteger gasused;
    @SerializedName("time")
    @Expose
    private String time;

    /**
     * No args constructor for use in serialization
     */
    public BlockResponse() {
    }

    public BlockResponse(String hash, BigInteger block, int maintxcount, int innertxcount, int tokentxcount, int totaltxcount, BigInteger gaslimit, BigInteger gasused, String time) {
        this.hash = hash;
        this.block = block;
        this.maintxcount = maintxcount;
        this.innertxcount = innertxcount;
        this.tokentxcount = tokentxcount;
        this.totaltxcount = totaltxcount;
        this.gaslimit = gaslimit;
        this.gasused = gasused;
        this.time = time;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigInteger getBlock() {
        return block;
    }

    public void setBlock(BigInteger block) {
        this.block = block;
    }

    public int getMaintxcount() {
        return maintxcount;
    }

    public void setMaintxcount(int maintxcount) {
        this.maintxcount = maintxcount;
    }

    public int getInnertxcount() {
        return innertxcount;
    }

    public void setInnertxcount(int innertxcount) {
        this.innertxcount = innertxcount;
    }

    public int getTokentxcount() {
        return tokentxcount;
    }

    public void setTokentxcount(int tokentxcount) {
        this.tokentxcount = tokentxcount;
    }

    public int getTotaltxcount() {
        return totaltxcount;
    }

    public void setTotaltxcount(int totaltxcount) {
        this.totaltxcount = totaltxcount;
    }

    public BigInteger getGaslimit() {
        return gaslimit;
    }

    public void setGaslimit(BigInteger gaslimit) {
        this.gaslimit = gaslimit;
    }

    public BigInteger getGasused() {
        return gasused;
    }

    public void setGasused(BigInteger gasused) {
        this.gasused = gasused;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
