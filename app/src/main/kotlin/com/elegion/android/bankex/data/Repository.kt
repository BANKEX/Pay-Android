package com.elegion.android.bankex.data

import android.content.Context
import com.elegion.android.bankex.data.local.PreferencesDataSource

import com.elegion.android.bankex.data.provider.ServiceProvider
import com.elegion.android.bankex.data.remote.rest.TemplateDataSource
import com.elegion.android.bankex.data.remote.rest.request.LoginRequest
import com.elegion.android.bankex.data.remote.rest.response.LoginResponse
import com.elegion.android.bankex.extension.kotlinx.coroutines.experimental.await
import okhttp3.Credentials
import java.util.Arrays

class Repository private constructor(context: Context) {
    private val preferencesDataSource: PreferencesDataSource = PreferencesDataSource(context)
    private val templateDataSource: TemplateDataSource = ServiceProvider.getServiceInstance(TemplateDataSource::class.java)
    private val clientId = "01d211db9c3e6dd5effd"
    private val clientSecret = "244d5f543b86f5dc6dd9555c6534cf8e86e46976"

    var onBoardingFlag: Boolean
        get() = preferencesDataSource.onBoardingFlag
        set(onBoarding) {
            preferencesDataSource.onBoardingFlag = onBoarding
        }

    suspend fun login(username: String, password: String): LoginResponse {
        val basicAuthHeader = Credentials.basic(username, password)
        val scopes = Arrays.asList("repo", "user")
        val request = LoginRequest(scopes, "e-legion.com", clientId, clientSecret)
        return templateDataSource.obtainOAuthToken(basicAuthHeader, request).await()
    }

    companion object {
        private const val DELAY = 1500L
        @JvmStatic
        private var instance: Repository? = null

        @JvmStatic
        @Synchronized
        fun get(context: Context): Repository {
            if (instance == null) {
                instance = Repository(context)
            }
            return instance as Repository
        }
    }
}