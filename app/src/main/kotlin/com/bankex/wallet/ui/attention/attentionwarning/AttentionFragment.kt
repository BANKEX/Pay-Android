package com.bankex.wallet.ui.attention.attentionwarning

import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bankex.wallet.R
import com.elegion.android.bankex.ui.attention.attentioncopy.AttentionCopyActivity
import com.elegion.android.bankex.ui.base.fragment.BaseNoInternetFragment
import kotlinx.android.synthetic.main.fr_attention.*

class AttentionFragment : BaseNoInternetFragment(), AttentionView {

    @InjectPresenter
    internal lateinit var presenter: AttentionPresenter

    override fun goToCopyPathPhrase() = startActivity(AttentionCopyActivity.makeIntent(activity!!))

    override fun tryAgain() {}

    override fun getLayout(): Int = R.layout.fr_attention

    override fun getViews(): Array<View> = arrayOf(content)

    override fun onResume() {
        super.onResume()
        nextButton.setOnClickListener { presenter.goToCopyPathPhrase() }
    }

    companion object {
        fun newInstance() = AttentionFragment()
    }
}
