package com.elegion.android.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mike
 */
final public class SequenceUtil {
    private static final AtomicInteger SEED = new AtomicInteger();

    private SequenceUtil() {
    }

    public static int getNextInt() {
        return SEED.incrementAndGet();
    }
}
