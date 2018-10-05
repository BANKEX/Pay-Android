package com.bankex.pay.domain.models.network;

/**
 * Запрос поиска по Адресу
 *
 * @author Gevork Safaryan on 29.05.2018
 */
public class AddressRequest {

    String addr;

    public AddressRequest(String addr) {
        this.addr = addr;
    }

    public String getAddr() {
        return addr;
    }
}
