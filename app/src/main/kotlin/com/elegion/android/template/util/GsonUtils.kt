package com.elegion.android.template.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtils {
    private lateinit var sRequestGson: Gson
    private lateinit var sGson: Gson

    @JvmStatic
    fun gson(): Gson {
        if (!::sGson.isInitialized) {
            sGson = Gson()
        }
        return sGson
    }

    @JvmStatic
    fun requestGson(): Gson {
        if (!::sRequestGson.isInitialized) {
            sRequestGson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'")
                .create()
        }
        return sRequestGson
    }
}