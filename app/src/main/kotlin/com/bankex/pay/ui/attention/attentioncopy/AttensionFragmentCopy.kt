package com.bankex.pay.ui.attention.attentioncopy

import android.view.View
import com.bankex.pay.R
import com.bankex.pay.ui.base.fragment.BaseNoInternetFragment
import kotlinx.android.synthetic.main.fr_attension2.*

/**
 * @author Denis Anisimov.
 */
class AttentionFragmentCopy : BaseNoInternetFragment(), AttentionViewCopy {

    override fun copyPathPhraseToClipBoard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showFinishButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finish() {

    }

    override fun tryAgain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayout(): Int = R.layout.fr_attension2

    override fun getViews(): Array<View> = arrayOf(content)

    companion object {
        fun newInstance() = AttentionFragmentCopy()
    }
}