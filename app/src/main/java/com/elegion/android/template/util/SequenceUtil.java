package com.elegion.android.template.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mike
 */
public final class SequenceUtil {
    private static final AtomicInteger SEED = new AtomicInteger();

    private SequenceUtil() {
    }

    public static int getNextInt() {
        return SEED.incrementAndGet();
    }
}
