package com.elegion.android.bankex.ui.startscreen

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.bankex.ui.base.presenter.BasePresenter

@InjectViewState
internal class StartPresenter() : BasePresenter<StartView>() {

    fun createWallet() {
        viewState.createWallet()
    }

    fun importWallet() {
        viewState.importWallet()
    }
}
