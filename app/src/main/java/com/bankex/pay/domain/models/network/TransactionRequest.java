package com.bankex.pay.domain.models.network;

/**
 * @author Gevork Safaryan on 21.06.2018
 */
public class TransactionRequest {

    String listId;

    String moduleId;

    RequestParams params;

    public TransactionRequest(String listId,
                              String moduleId,
                              RequestParams params) {
        this.listId = listId;
        this.moduleId = moduleId;
        this.params = params;
    }

}
