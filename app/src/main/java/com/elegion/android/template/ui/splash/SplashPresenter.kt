package com.elegion.android.template.ui.splash

import android.text.TextUtils

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.ui.base.presenter.BasePresenter

@InjectViewState
internal class SplashPresenter(private val repository: Repository) : BasePresenter<SplashView>() {

    fun timerFinish() {
        val loginToken = repository.loginToken
        if (TextUtils.isEmpty(loginToken)) {
            viewState.openLogin()
        } else {
            viewState.openFeatures()
        }
    }
}
