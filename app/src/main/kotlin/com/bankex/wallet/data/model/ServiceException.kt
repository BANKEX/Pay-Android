package com.bankex.wallet.data.model

class ServiceException(message: String) : Exception(message) {
    val error: ErrorEnvelope
    init {
        error = ErrorEnvelope(message)
    }
}
