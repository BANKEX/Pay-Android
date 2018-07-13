package com.bankex.pay.ui.attention.attentionwarning

import com.arellomobile.mvp.InjectViewState
import com.bankex.pay.ui.base.presenter.BasePresenter

/**
 * @author Denis Anisimov.
 */
@InjectViewState
class AttentionPresenter : BasePresenter<AttentionView>() {
    fun goToCopyPathPhrase() = viewState.goToCopyPathPhrase()
}