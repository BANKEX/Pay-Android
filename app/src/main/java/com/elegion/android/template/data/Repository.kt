package com.elegion.android.template.data

import android.content.Context
import com.elegion.android.template.data.local.PreferencesRepository
import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.data.provider.ServiceProvider
import com.elegion.android.template.data.remote.rest.TemplateService
import com.elegion.android.template.data.remote.rest.request.LoginRequest
import com.elegion.android.template.data.remote.rest.response.LoginResponse
import io.reactivex.Flowable
import okhttp3.Credentials
import java.util.*
import java.util.concurrent.TimeUnit

class Repository private constructor(context: Context) {
    private val preferencesRepository: PreferencesRepository = PreferencesRepository(context)
    private val templateService: TemplateService = ServiceProvider.getServiceInstance(TemplateService::class.java)
    private val clientId = "01d211db9c3e6dd5effd"
    private val clientSecret = "244d5f543b86f5dc6dd9555c6534cf8e86e46976"

    var loginToken: String?
        get() = preferencesRepository.loginToken
        set(loginToken) {
            preferencesRepository.loginToken = loginToken
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
        }.delay(1500, TimeUnit.MILLISECONDS)
    }

    companion object {
        private lateinit var sInstance: Repository

        fun get(context: Context): Repository {
            if (!::sInstance.isInitialized) {
                sInstance = Repository(context)
            }
            return sInstance
        }
    }
}
