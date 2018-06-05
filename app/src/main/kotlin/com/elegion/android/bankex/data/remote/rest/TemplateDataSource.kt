package com.elegion.android.bankex.data.remote.rest

import com.elegion.android.bankex.data.remote.rest.request.LoginRequest
import com.elegion.android.bankex.data.remote.rest.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TemplateDataSource {
    @POST("authorizations")
    fun obtainOAuthToken(
        @Header("Authorization") basicAuthHeader: String,
        @Body params: LoginRequest
    ): Call<LoginResponse>
}
