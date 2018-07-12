package com.bankex.wallet.ui.startscreen

import com.arellomobile.mvp.InjectViewState
import com.bankex.wallet.ui.base.presenter.BasePresenter

@InjectViewState
internal class StartPresenter() : BasePresenter<StartView>() {

    fun createWallet() {
        viewState.createWallet()
    }

    fun importWallet() {
        viewState.importWallet()
    }
}
