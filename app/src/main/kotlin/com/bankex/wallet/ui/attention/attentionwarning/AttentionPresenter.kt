package com.bankex.wallet.ui.attention.attentionwarning

import com.arellomobile.mvp.InjectViewState
import com.bankex.wallet.ui.base.presenter.BasePresenter

/**
 * @author Denis Anisimov.
 */
@InjectViewState
class AttentionPresenter : BasePresenter<AttentionView>() {
    fun goToCopyPathPhrase() = viewState.goToCopyPathPhrase()
}