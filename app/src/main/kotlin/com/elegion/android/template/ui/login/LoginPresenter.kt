package com.elegion.android.template.ui.login

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.data.remote.rest.response.LoginResponse
import com.elegion.android.template.ui.base.error.ErrorHandler
import com.elegion.android.template.ui.base.presenter.BasePresenter
import com.elegion.android.template.util.RxUtils
import io.reactivex.disposables.Disposable

@InjectViewState
internal class LoginPresenter(private val repository: Repository) : BasePresenter<LoginView>() {
    private var email: String = ""
    private var password: String = ""
    private var errorHandler = ErrorHandler.create(viewState, repository, viewState)
    private var loginSubscription: Disposable? = null

    fun login() {
        if (RxUtils.isNullOrDisposed(loginSubscription)) {
            removeDisposable(loginSubscription)
            loginSubscription = repository.login(email, password)
                .compose<LoginResponse> { RxUtils.async(it) }
                .compose(RxUtils.loading(viewState))
                .compose(errorHandler.transformer())
                .subscribe({ this.handleLogin(it) }, { RxUtils.errorLogE(it) })
            addDisposable(loginSubscription)
        }
    }

    private fun handleLogin(response: LoginResponse) {
        repository.loginToken = response.token
        viewState.loginSuccessful()
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
