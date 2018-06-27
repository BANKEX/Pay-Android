package com.elegion.android.bankex.ui.createwallet

import com.elegion.android.bankex.data.Repository
import com.elegion.android.bankex.ui.base.presenter.BasePresenter
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CreateWalletPresenter(private val repository: Repository, private val mWalletGenerationView: CreateWalletView, val mPassword: String)  : BasePresenter<CreateWalletView>(){

    fun generateWallet(password: String) {
        launch(UI) {
            try {
                val createWallet = async { repository.walletDataSource.createWallet(password) }.await()
                mWalletGenerationView.showGeneratedWallet(" Address " + createWallet.address)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun setEmail(toString: String) {

    }

    fun setPassword(toString: String) {

    }

}
