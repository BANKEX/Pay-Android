package com.elegion.android.bankex.ui.attention.attentionwarning

import com.arellomobile.mvp.InjectViewState
import com.elegion.android.bankex.ui.base.presenter.BasePresenter

/**
 * @author Denis Anisimov.
 */
@InjectViewState
class AttentionPresenter : BasePresenter<AttentionView>() {
    fun goToCopyPathPhrase() = viewState.goToCopyPathPhrase()
}