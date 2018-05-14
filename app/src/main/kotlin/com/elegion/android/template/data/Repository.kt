package com.elegion.android.template.data

import android.content.Context
import com.elegion.android.template.data.local.PreferencesDataSource
import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.data.provider.ServiceProvider
import com.elegion.android.template.data.remote.rest.TemplateDataSource
import com.elegion.android.template.data.remote.rest.request.LoginRequest
import com.elegion.android.template.data.remote.rest.response.LoginResponse
import com.elegion.android.template.extension.kotlinx.coroutines.experimental.await
import kotlinx.coroutines.experimental.delay
import okhttp3.Credentials
import java.util.Arrays

class Repository private constructor(context: Context) {
    private val preferencesDataSource: PreferencesDataSource = PreferencesDataSource(context)
    private val templateDataSource: TemplateDataSource = ServiceProvider.getServiceInstance(TemplateDataSource::class.java)
    private val clientId = "01d211db9c3e6dd5effd"
    private val clientSecret = "244d5f543b86f5dc6dd9555c6534cf8e86e46976"

    var loginToken: String?
        get() = preferencesDataSource.loginToken
        set(loginToken) {
            preferencesDataSource.loginToken = loginToken
        }

    suspend fun login(username: String, password: String): LoginResponse {
        val basicAuthHeader = Credentials.basic(username, password)
        val scopes = Arrays.asList("repo", "user")
        val request = LoginRequest(scopes, "e-legion.com", clientId, clientSecret)
        return templateDataSource.obtainOAuthToken(basicAuthHeader, request).await()
    }

    suspend fun getFeatures(offset: Int, count: Int): MutableList<Feature> {
        delay(DELAY)
        val features = mutableListOf<Feature>()
        val amount = offset + count
        for (i in offset until amount) {
            features.add(Feature("Title $i", "Description $i"))
        }
        return features
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
