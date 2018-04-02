package com.elegion.android.template.data

import android.content.Context
import com.elegion.android.template.data.local.PreferencesDataSource
import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.data.provider.ServiceProvider
import com.elegion.android.template.data.remote.rest.TemplateService
import com.elegion.android.template.data.remote.rest.request.LoginRequest
import com.elegion.android.template.data.remote.rest.response.LoginResponse
import io.reactivex.Flowable
import okhttp3.Credentials
import java.util.Arrays
import java.util.concurrent.TimeUnit

class Repository private constructor(context: Context) {
    private val preferencesDataSource: PreferencesDataSource = PreferencesDataSource(context)
    private val templateService: TemplateService = ServiceProvider.getServiceInstance(TemplateService::class.java)
    private val clientId = "01d211db9c3e6dd5effd"
    private val clientSecret = "244d5f543b86f5dc6dd9555c6534cf8e86e46976"

    var loginToken: String?
        get() = preferencesDataSource.loginToken
        set(loginToken) {
            preferencesDataSource.loginToken = loginToken
        }

    fun login(username: String, password: String): Flowable<LoginResponse> {
        val basicAuthHeader = Credentials.basic(username, password)
        val scopes = Arrays.asList("repo", "user")
        val request = LoginRequest(scopes, "e-legion.com", clientId, clientSecret)
        return templateService.obtainOAuthToken(basicAuthHeader, request)
    }

    fun getFeatures(offset: Int, count: Int): Flowable<List<Feature>> {
        return Flowable.defer {
            val features = ArrayList<Feature>()
            val amount = offset + count
            for (i in offset until amount) {
                features.add(Feature("Title $i", "Description $i"))
            }
            Flowable.just<List<Feature>>(features)
        }.delay(DELAY, TimeUnit.MILLISECONDS)
    }

    companion object {
        private const val DELAY = 1500L
        @JvmStatic
        private var sInstance: Repository? = null

        @JvmStatic
        @Synchronized
        fun get(context: Context): Repository {
            if (sInstance == null) {
                sInstance = Repository(context)
            }
            return sInstance as Repository
        }
    }
}
