package com.elegion.android.template.ui.login

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.ui.base.error.ErrorHandler
import com.elegion.android.template.ui.base.presenter.BasePresenter
import kotlinx.coroutines.experimental.Job

@InjectViewState
internal class LoginPresenter(private val repository: Repository) : BasePresenter<LoginView>() {
    private var email: String = ""
    private var password: String = ""
    private var errorHandler = ErrorHandler.create(viewState, repository, viewState)
    private var loginJob : Job? = null

    fun login() {
        if (loginJob?.isCompleted != false) {
            loginJob = launchLoadingErrorJob(errorHandler, viewState) {
                val response = repository.login(email, password)
                repository.loginToken = response.token
                viewState.loginSuccessful()
            }
            addCancellableJob(loginJob)
        }
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun letMeIn() {
        repository.loginToken = "LetMeInToken"
        viewState.loginSuccessful()
    }
}
