package com.bankex.pay.domain.models.network;

/**
 * @author Gevork Safaryan on 21.06.2018
 */
public class RequestParams {

    String entityId;
    int page;
    int size;

    public RequestParams(String entityId,
                         int page,
                         int size) {
        this.entityId = entityId;
        this.page = page;
        this.size = size;
    }
}
