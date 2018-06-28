package com.elegion.android.bankex.ui.attention

import com.arellomobile.mvp.MvpView

/**
 * @author Denis Anisimov.
 */
interface AttentionView : MvpView {
    fun showGeneratedWallet(walletAddress: String)
}