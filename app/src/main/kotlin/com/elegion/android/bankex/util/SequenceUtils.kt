package com.elegion.android.bankex.util

import java.util.concurrent.atomic.AtomicInteger

object SequenceUtils {
    private val SEED = AtomicInteger()

    val nextInt: Int
        get() = SEED.incrementAndGet()
}
