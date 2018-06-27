package com.elegion.android.bankex.ui.createwallet

import com.arellomobile.mvp.MvpView

interface CreateWalletView : MvpView {
    fun showGeneratedWallet(walletAddress: String)
}
