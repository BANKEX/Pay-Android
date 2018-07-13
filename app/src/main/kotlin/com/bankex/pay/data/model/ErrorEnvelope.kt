package com.bankex.pay.data.model

import com.bankex.pay.C

class ErrorEnvelope @JvmOverloads constructor(val code: Int, val message: String?, private val throwable: Throwable? = null) {

    constructor(message: String?) : this(C.ErrorCode.UNKNOWN, message) {}
}
