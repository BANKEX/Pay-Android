package com.elegion.android.util;

import android.support.annotation.Nullable;

import java.util.Collection;

/**
 * @author Nikita Bumakov
 */
public abstract class CollectionUtil {

    public static boolean isEmpty(@Nullable Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
