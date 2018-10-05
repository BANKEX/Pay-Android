package com.bankex.pay.domain.models.transaction;

import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.network.Token;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Gevork Safaryan on 22.06.2018
 */
public class TransactionModel extends BaseBankexModel {

    private String id;
    private String hash;
    private BigInteger block;
    private String addrfrom;
    private String addrto;
    private String isotime;
    private String type;
    private Integer status;
    private String error;
    private Integer iscontract;
    private Integer isinner;
    private BigDecimal value;
    private Token token;
    private String txfee;
    private BigInteger gasused;
    private BigInteger gascost;
    private String currentAddress;

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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
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

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionModel that = (TransactionModel) o;
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
                Objects.equal(gascost, that.gascost) &&
                Objects.equal(currentAddress, that.currentAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, hash, block, addrfrom, addrto, isotime, type, status, error, iscontract, isinner, value, token, txfee, gasused, gascost, currentAddress);
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
                .add("currentAddress", currentAddress)
                .toString();
    }
}
