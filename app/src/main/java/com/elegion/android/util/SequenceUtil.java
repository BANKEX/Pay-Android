package com.elegion.android.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mike
 */
public class SequenceUtil {
    public static final AtomicInteger seed = new AtomicInteger();

    public static int getNextInt() {
        return seed.incrementAndGet();
    }
}
