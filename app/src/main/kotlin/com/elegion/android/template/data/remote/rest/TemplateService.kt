package com.elegion.android.template.data.remote.rest

import com.elegion.android.template.data.remote.rest.request.LoginRequest
import com.elegion.android.template.data.remote.rest.response.LoginResponse

import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TemplateService {
    @POST("authorizations")
    fun obtainOAuthToken(
        @Header("Authorization") basicAuthHeader: String,
        @Body params: LoginRequest
    ): Flowable<LoginResponse>
}
