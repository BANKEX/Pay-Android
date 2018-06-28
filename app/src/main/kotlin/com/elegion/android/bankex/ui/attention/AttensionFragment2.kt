package com.elegion.android.bankex.ui.attention

import android.view.View
import com.elegion.android.bankex.R
import com.elegion.android.bankex.ui.base.fragment.BaseNoInternetFragment
import com.elegion.android.bankex.ui.onboarding.OnBoardingFragment
import kotlinx.android.synthetic.main.fr_wallet.*

/**
 * @author Denis Anisimov.
 */
class AttentionFragment2 : BaseNoInternetFragment() {

    override fun tryAgain() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayout(): Int = R.layout.fr_attension2

    override fun getViews(): Array<View> = arrayOf(content)

    companion object {
        fun newInstance() = OnBoardingFragment()
    }
}