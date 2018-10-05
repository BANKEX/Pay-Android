package com.bankex.pay.domain.models.network;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Gevork Safaryan on 18.06.2018
 */
public class AddressResponse {

    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("mainTxCount")
    @Expose
    private int maintxcount;
    @SerializedName("innerTxCount")
    @Expose
    private int innertxcount;
    @SerializedName("tokenTxCount")
    @Expose
    private int tokentxcount;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("decimals")
    @Expose
    private int decimals;
    @SerializedName("totalTokens")
    @Expose
    private int totaltokens;

    /**
     * No args constructor for use in serialization
     */
    public AddressResponse() {
    }

    public AddressResponse(String addr, int maintxcount, int innertxcount, int tokentxcount, String balance, int decimals, int totaltokens) {
        this.addr = addr;
        this.maintxcount = maintxcount;
        this.innertxcount = innertxcount;
        this.tokentxcount = tokentxcount;
        this.balance = balance;
        this.decimals = decimals;
        this.totaltokens = totaltokens;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public int getTotaltokens() {
        return totaltokens;
    }

    public void setTotaltokens(int totaltokens) {
        this.totaltokens = totaltokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AddressResponse that = (AddressResponse) o;
        return maintxcount == that.maintxcount &&
                innertxcount == that.innertxcount &&
                tokentxcount == that.tokentxcount &&
                decimals == that.decimals &&
                totaltokens == that.totaltokens &&
                Objects.equal(addr, that.addr) &&
                Objects.equal(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(addr, maintxcount, innertxcount, tokentxcount, balance, decimals, totaltokens);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("addr", addr)
                .add("maintxcount", maintxcount)
                .add("innertxcount", innertxcount)
                .add("tokentxcount", tokentxcount)
                .add("balance", balance)
                .add("decimals", decimals)
                .add("totaltokens", totaltokens)
                .toString();
    }
}
