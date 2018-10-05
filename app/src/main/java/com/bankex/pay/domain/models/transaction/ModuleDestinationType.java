package com.bankex.pay.domain.models.transaction;

/**
 * @author Gevork Safaryan on 21.06.2018
 */
public enum ModuleDestinationType {
    TRANSACTIONS("transactions"),
    TOKENS("tokens"),
    ADDRESS("address"),
    BLOCK("block");

    private String mBlock;

    ModuleDestinationType(String block) {
        mBlock = block;
    }

    @Override
    public String toString() {
        return mBlock;
    }
}
