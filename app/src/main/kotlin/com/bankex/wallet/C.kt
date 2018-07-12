package com.bankex.wallet

public object C {

    public val IMPORT_REQUEST_CODE = 1001
    public val EXPORT_REQUEST_CODE = 1002
    public val SHARE_REQUEST_CODE = 1003

    public val ETHEREUM_NETWORK_NAME = "Ethereum"
    public val CLASSIC_NETWORK_NAME = "Ethereum Classic"
    public val POA_NETWORK_NAME = "POA Network"
    public val KOVAN_NETWORK_NAME = "Kovan (Test)"
    public val ROPSTEN_NETWORK_NAME = "Ropsten (Test)"

    public val ETHEREUM_TIKER = "ethereum"
    public val POA_TIKER = "poa"

    public val USD_SYMBOL = "$"
    public val ETH_SYMBOL = "ETH"
    public val POA_SYMBOL = "POA"
    public val ETC_SYMBOL = "ETC"

    public val GWEI_UNIT = "Gwei"

    public val EXTRA_ADDRESS = "ADDRESS"
    public val EXTRA_CONTRACT_ADDRESS = "CONTRACT_ADDRESS"
    public val EXTRA_DECIMALS = "DECIMALS"
    public val EXTRA_SYMBOL = "SYMBOL"
    public val EXTRA_SENDING_TOKENS = "SENDING_TOKENS"
    public val EXTRA_TO_ADDRESS = "TO_ADDRESS"
    public val EXTRA_AMOUNT = "AMOUNT"
    public val EXTRA_GAS_PRICE = "GAS_PRICE"
    public val EXTRA_GAS_LIMIT = "GAS_LIMIT"

    public val COINBASE_WIDGET_CODE = "88d6141a-ff60-536c-841c-8f830adaacfd"
    public val SHAPESHIFT_KEY = "c4097b033e02163da6114fbbc1bf15155e759ddfd8352c88c55e7fef162e901a800e7eaecf836062a0c075b2b881054e0b9aa2324be7bc3694578493faf59af4"
    public val CHANGELLY_REF_ID = "968d4f0f0bf9"
    public val DONATION_ADDRESS = "0x9f8284ce2cf0c8ce10685f537b1fff418104a317"

    public val DEFAULT_GAS_PRICE = "21000000000"
    public val DEFAULT_GAS_LIMIT = "90000"
    public val DEFAULT_GAS_LIMIT_FOR_TOKENS = "144000"
    public val GAS_LIMIT_MIN = 21000L
    public val GAS_LIMIT_MAX = 300000L
    public val GAS_PRICE_MIN = 1000000000L
    public val NETWORK_FEE_MAX = 20000000000000000L
    public val ETHER_DECIMALS = 18

    interface ErrorCode {
        companion object {

            public val UNKNOWN = 1
            public val CANT_GET_STORE_PASSWORD = 2
        }
    }

    interface Key {
        companion object {
            public val WALLET = "wallet"
            public val TRANSACTION = "transaction"
            public val SHOULD_SHOW_SECURITY_WARNING = "should_show_security_warning"
        }
    }
}
