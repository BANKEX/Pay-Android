package com.elegion.android.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Mike
 */
final public class GsonUtils {
    private static Gson sRequestGson;

    private static Gson sGson;

    private GsonUtils() {
    }

    public static Gson gson() {
        if (sGson == null) {
            sGson = new Gson();
        }
        return sGson;
    }

    public static Gson requestGson() {
        if (sRequestGson == null) {
            sRequestGson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'")
                    .create();
        }
        return sRequestGson;
    }

}