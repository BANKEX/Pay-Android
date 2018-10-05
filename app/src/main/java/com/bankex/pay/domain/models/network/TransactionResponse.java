package com.bankex.pay.domain.models.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

/**
 * @author Gevork Safaryan on 21.06.2018
 */
public class TransactionResponse {

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
    private String type;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("iscontract")
    @Expose
    private Integer iscontract;
    @SerializedName("isinner")
    @Expose
    private Integer isinner;
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

    /**
     * No args constructor for use in serialization
     */
    public TransactionResponse() {
    }

    /**
     * @param isotime
     * @param error
     * @param hash
     * @param status
     * @param gasused
     * @param block
     * @param isinner
     * @param addrfrom
     * @param type
     * @param iscontract
     * @param id
     * @param addrto
     * @param token
     * @param value
     * @param txfee
     * @param gascost
     */
    public TransactionResponse(String id, String hash, BigInteger block, String addrfrom, String addrto, String isotime, String type, Integer status, String error, Integer iscontract, Integer isinner, String value, Token token, String txfee, BigInteger gasused, BigInteger gascost) {
        super();
        this.id = id;
        this.hash = hash;
        this.block = block;
        this.addrfrom = addrfrom;
        this.addrto = addrto;
        this.isotime = isotime;
        this.type = type;
        this.status = status;
        this.error = error;
        this.iscontract = iscontract;
        this.isinner = isinner;
        this.value = value;
        this.token = token;
        this.txfee = txfee;
        this.gasused = gasused;
        this.gascost = gascost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddrfrom() {
        return addrfrom;
    }

    public void setAddrfrom(String addrfrom) {
        this.addrfrom = addrfrom;
    }

    public String getAddrto() {
        return addrto;
    }

    public void setAddrto(String addrto) {
        this.addrto = addrto;
    }

    public String getIsotime() {
        return isotime;
    }

    public void setIsotime(String isotime) {
        this.isotime = isotime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getIscontract() {
        return iscontract;
    }

    public void setIscontract(Integer iscontract) {
        this.iscontract = iscontract;
    }

    public Integer getIsinner() {
        return isinner;
    }

    public void setIsinner(Integer isinner) {
        this.isinner = isinner;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getTxfee() {
        return txfee;
    }

    public void setTxfee(String txfee) {
        this.txfee = txfee;
    }

    public BigInteger getGasused() {
        return gasused;
    }

    public void setGasused(BigInteger gasused) {
        this.gasused = gasused;
    }

    public BigInteger getGascost() {
        return gascost;
    }

    public void setGascost(BigInteger gascost) {
        this.gascost = gascost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionResponse that = (TransactionResponse) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(hash, that.hash) &&
                Objects.equal(block, that.block) &&
                Objects.equal(addrfrom, that.addrfrom) &&
                Objects.equal(addrto, that.addrto) &&
                Objects.equal(isotime, that.isotime) &&
                Objects.equal(type, that.type) &&
                Objects.equal(status, that.status) &&
                Objects.equal(error, that.error) &&
                Objects.equal(iscontract, that.iscontract) &&
                Objects.equal(isinner, that.isinner) &&
                Objects.equal(value, that.value) &&
                Objects.equal(token, that.token) &&
                Objects.equal(txfee, that.txfee) &&
                Objects.equal(gasused, that.gasused) &&
                Objects.equal(gascost, that.gascost);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, hash, block, addrfrom, addrto, isotime, type, status, error, iscontract, isinner, value, token, txfee, gasused, gascost);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("hash", hash)
                .add("block", block)
                .add("addrfrom", addrfrom)
                .add("addrto", addrto)
                .add("isotime", isotime)
                .add("type", type)
                .add("status", status)
                .add("error", error)
                .add("iscontract", iscontract)
                .add("isinner", isinner)
                .add("value", value)
                .add("token", token)
                .add("txfee", txfee)
                .add("gasused", gasused)
                .add("gascost", gascost)
                .toString();
    }
}

