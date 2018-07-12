package com.bankex.wallet.data.model

class ApiErrorException(private val errorEnvelope: ErrorEnvelope) : Exception(errorEnvelope.message)
