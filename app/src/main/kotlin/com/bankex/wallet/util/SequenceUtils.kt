package com.bankex.wallet.util

import java.util.concurrent.atomic.AtomicInteger

object SequenceUtils {
    private val SEED = AtomicInteger()

    val nextInt: Int
        get() = SEED.incrementAndGet()
}
