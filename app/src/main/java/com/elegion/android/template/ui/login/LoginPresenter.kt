package com.elegion.android.template.ui.login

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.data.remote.rest.response.LoginResponse
import com.elegion.android.template.ui.base.presenter.BasePresenter
import com.elegion.android.template.util.RxUtils
import io.reactivex.disposables.Disposable

@InjectViewState
internal class LoginPresenter(private val mRepository: Repository) : BasePresenter<LoginView>() {
    private var mEmail: String? = null
    private var mPassword: String? = null
    private var mLoginSubscription: Disposable? = null

    fun login() {
        if (RxUtils.isNullOrUnsubscribed(mLoginSubscription)) {
            removeDisposable(mLoginSubscription!!)
            mLoginSubscription = mRepository.login(mEmail, mPassword)
                    .compose<LoginResponse>({ RxUtils.async(it) })
                    .compose(RxUtils.loading(viewState))
                    .compose(RxUtils.errorTransformer(viewState, mRepository, viewState))
                    .subscribe({ this.handleLogin(it) }, { RxUtils.errorLogE(it) })
            addDisposable(mLoginSubscription!!)
        }
    }

    private fun handleLogin(response: LoginResponse) {
        mRepository.saveLoginToken(response.token)
        viewState.loginSuccessful()
    }

    fun setEmail(email: String) {
        mEmail = email
    }

    fun setPassword(password: String) {
        mPassword = password
    }
}
