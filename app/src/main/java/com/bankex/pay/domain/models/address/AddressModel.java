package com.bankex.pay.domain.models.address;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.math.BigDecimal;

/**
 * Модель вью компонента, отображающего базовую инфу по адресу
 *
 * @author Pavel Apanovskiy on 18.06.2018
 */
public class AddressModel extends BaseAddressModel {

    private String addr;
    private BigDecimal balance;
    private int totaltokens;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
        AddressModel that = (AddressModel) o;
        return totaltokens == that.totaltokens &&
                Objects.equal(addr, that.addr) &&
                Objects.equal(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(addr, balance, totaltokens);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("addr", addr)
                .add("balance", balance)
                .add("totaltokens", totaltokens)
                .toString();
    }
}
