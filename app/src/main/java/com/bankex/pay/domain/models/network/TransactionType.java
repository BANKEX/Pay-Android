package com.bankex.pay.domain.models.network;

/**
 * Тип транзакции
 *
 * @author Gevork Safaryan on 31.05.2018
 */
public enum TransactionType {
    TX("tx"),
    CALL("call"),
    CREATE("create"),
    SUICIDE("suicide"),
    TOKEN("token");

    private String type;

    TransactionType(String type) {
        this.type = type;
    }
}
