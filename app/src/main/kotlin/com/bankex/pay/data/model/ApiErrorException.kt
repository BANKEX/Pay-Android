package com.bankex.pay.data.model

class ApiErrorException(private val errorEnvelope: ErrorEnvelope) : Exception(errorEnvelope.message)
