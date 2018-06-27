package com.elegion.android.bankex.data.model

class ApiErrorException(private val errorEnvelope: ErrorEnvelope) : Exception(errorEnvelope.message)
