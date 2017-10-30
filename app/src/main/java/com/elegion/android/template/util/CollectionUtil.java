package com.elegion.android.template.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Mike
 */
public abstract class CollectionUtil {

    public static boolean isEmpty(@Nullable Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static int size(@Nullable Collection collection) {
        return isEmpty(collection) ? 0 : collection.size();
    }

    @NonNull
    public static <T> List<T> emptyIfNull(@Nullable List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }
}
