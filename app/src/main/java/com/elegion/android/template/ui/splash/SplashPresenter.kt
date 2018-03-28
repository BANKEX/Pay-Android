package com.elegion.android.template.ui.splash

import android.text.TextUtils

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.ui.base.presenter.BasePresenter

@InjectViewState
internal class SplashPresenter(private val mRepository: Repository) : BasePresenter<SplashView>() {

    fun timerFinish() {
        val loginToken = mRepository.loginToken
        if (TextUtils.isEmpty(loginToken)) {
            viewState.openLogin()
        } else {
            viewState.openFeatures()
        }
    }
}
