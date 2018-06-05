package com.elegion.android.bankex.ui.onboarding

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.bankex.data.Repository
import com.elegion.android.bankex.ui.base.error.ErrorHandler
import com.elegion.android.bankex.ui.base.presenter.BasePresenter
import kotlinx.coroutines.experimental.Job

@InjectViewState
internal class OnBoardingPresenter(private val repository: Repository) : BasePresenter<OnBoardingView>() {

    private var errorHandler = ErrorHandler.create(viewState, repository, viewState)
    private var loginJob: Job? = null


    fun letMeIn() {
        repository.onBoardingFlag = true
        viewState.onBoardingConfirmed()
    }
}
