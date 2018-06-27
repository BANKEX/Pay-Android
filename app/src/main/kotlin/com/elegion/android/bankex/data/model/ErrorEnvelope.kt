package com.elegion.android.bankex.data.model

import com.elegion.android.bankex.C

class ErrorEnvelope @JvmOverloads constructor(val code: Int, val message: String?, private val throwable: Throwable? = null) {

    constructor(message: String?) : this(C.ErrorCode.UNKNOWN, message) {}
}
