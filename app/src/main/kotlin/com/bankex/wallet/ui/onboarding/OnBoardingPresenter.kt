package com.bankex.wallet.ui.onboarding

import com.arellomobile.mvp.InjectViewState
import com.bankex.wallet.data.Repository
import com.bankex.wallet.ui.base.error.ErrorHandler
import com.bankex.wallet.ui.base.presenter.BasePresenter
import kotlinx.coroutines.experimental.Job

@InjectViewState
internal class OnBoardingPresenter(private val repository: Repository) : BasePresenter<OnBoardingView>() {

    private var errorHandler = ErrorHandler.create(viewState, repository, viewState)
    private var loginJob: Job? = null

    fun changeLabel() {
        viewState.onBoardingSetLabelStart()
    }

    fun onBoardingNext(currentItem: Int, childCount: Int) {
        when (currentItem) {
            childCount -> finishOnboarding()
            else -> viewState.onBoardingNext(currentItem)
        }
    }

    fun finishOnboarding() {
        repository.onBoardingFlag = true
        viewState.onBoardingConfirmed()
    }
}
