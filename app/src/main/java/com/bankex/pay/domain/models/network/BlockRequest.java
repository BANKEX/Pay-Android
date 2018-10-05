package com.bankex.pay.domain.models.network;

/**
 * Запрос поиска по блоку
 *
 * @author Gevork Safaryan on 29.05.2018
 */
public class BlockRequest {

    String block;

    public BlockRequest(String block) {
        this.block = block;
    }

    public String getBlock() {
        return block;
    }
}
