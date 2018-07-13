package com.bankex.pay.ui.createwallet

import com.arellomobile.mvp.InjectViewState
import com.bankex.pay.data.Repository
import com.bankex.pay.ui.base.presenter.BasePresenter
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
@InjectViewState
class CreateWalletPresenter(private val repository: Repository) : BasePresenter<CreateWalletView>() {

    var password = ""
        set(value) {
            field = value
            viewState.showButtonEnabled(checkPassword())
        }

    var confPassword = ""
        set(value) {
            field = value
            viewState.showButtonEnabled(checkPassword())
        }

    fun generateWallet() {
        launch(UI) {
            try {
                val createWallet = async { repository.walletDataSource.createWallet(password) }.await()
                viewState.showGeneratedWallet(" Address " + createWallet.address)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun checkPassword(): Boolean {
        if (password.length == 0 || confPassword.length == 0) return false
        if (password.equals(confPassword)) return true
        return false
    }

}
