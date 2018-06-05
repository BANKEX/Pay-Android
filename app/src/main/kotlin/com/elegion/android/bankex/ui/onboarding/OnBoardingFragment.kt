package com.elegion.android.bankex.ui.onboarding

import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.bankex.R
import com.elegion.android.bankex.data.Repository
import com.elegion.android.bankex.ui.base.fragment.BaseNoInternetFragment
import kotlinx.android.synthetic.main.fr_login.*

class OnBoardingFragment : BaseNoInternetFragment(), OnBoardingView {

    @InjectPresenter
    internal lateinit var mPresenter: OnBoardingPresenter

    @ProvidePresenter
    internal fun providePresenter(): OnBoardingPresenter = OnBoardingPresenter(Repository.get(activity!!))

    override fun getLayout(): Int = R.layout.fr_login

    override fun onResume() {
        super.onResume()
        loginLetMeInBtn.setOnClickListener { mPresenter.letMeIn() }
    }


    override fun getViews(): Array<View> = arrayOf(loginTopLayout)


    override fun onBoardingConfirmed() {

    }
    override fun onBoardingSkipped() {

    }

    override fun tryAgain() {

    }

    companion object {
        fun newInstance() = OnBoardingFragment()
    }
}
