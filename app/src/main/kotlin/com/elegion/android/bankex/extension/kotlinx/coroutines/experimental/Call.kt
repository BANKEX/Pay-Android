package com.elegion.android.bankex.extension.kotlinx.coroutines.experimental

import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@Suppress("NOTHING_TO_INLINE")
suspend inline fun <T> Call<T>.await(): T = suspendCancellableCoroutine {
    enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            it.resumeWithException(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful)
                it.resume(response.body()!!)
            else
                it.resumeWithException(IOException(response.message()))
        }
    })
}