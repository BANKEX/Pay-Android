package com.elegion.android.bankex.data.model

class ServiceException(message: String) : Exception(message) {
    val error: ErrorEnvelope
    init {
        error = ErrorEnvelope(message)
    }
}
