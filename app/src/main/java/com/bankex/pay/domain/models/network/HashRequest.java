package com.bankex.pay.domain.models.network;

/**
 * Запрос поиска по Хешу
 *
 * @author Gevork Safaryan on 29.05.2018
 */
public class HashRequest {

    String hash;

    public HashRequest(String hash) {
        this.hash = hash;
    }
}
