package com.elegion.android.template.util

import java.util.concurrent.atomic.AtomicInteger

object SequenceUtil {
    private val SEED = AtomicInteger()

    val nextInt: Int
        get() = SEED.incrementAndGet()
}
