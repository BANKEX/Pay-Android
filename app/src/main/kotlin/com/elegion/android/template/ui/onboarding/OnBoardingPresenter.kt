package com.elegion.android.template.ui.onboarding

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.template.data.Repository
import com.elegion.android.template.ui.base.error.ErrorHandler
import com.elegion.android.template.ui.base.presenter.BasePresenter
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
