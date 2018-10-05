package com.bankex.pay.domain.models.transaction;

/**
 * @author Gevork Safaryan on 21.06.2018
 */
public enum ListType {
    ETH("listOfETH"),
    TOKEN("listOfTokens"),
    TOKENBALANCE("listOfTokenBalance");

    private String mListOfTokens;

    ListType(String listOfTokens) {
        mListOfTokens = listOfTokens;
    }

    public String getListOfTokens() {
        return mListOfTokens;
    }
}
