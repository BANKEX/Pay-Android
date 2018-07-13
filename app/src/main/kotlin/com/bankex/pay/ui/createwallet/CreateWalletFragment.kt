package com.bankex.pay.ui.createwallet

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bankex.pay.R
import com.bankex.pay.ui.base.fragment.BaseTabViewPagerFragment
import com.bankex.pay.ui.base.fragment.TabWalletView

class CreateWalletFragment : BaseTabViewPagerFragment(), TabWalletView {

    @InjectPresenter
    internal lateinit var presenterCreate: TabWalletPresenter

    @ProvidePresenter
    internal fun providePresenter(): TabWalletPresenter = TabWalletPresenter()

    override fun getAdapter(): PagerAdapter = CreateWalletPagerAdapter(fragmentManager)

    companion object {
        fun newInstance() = CreateWalletFragment()
    }

    private val REQUEST_PERMISSION_WRITE_STORAGE = 0

    private inner class CreateWalletPagerAdapter(val supportFragmentManager: FragmentManager?) : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment {
            val fragment = PrivateKeyCreateFragment.newInstance()
            return fragment
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            return if (position == 0) {
                getString(R.string.private_key)
            } else if (position == 1) {
                getString(R.string.passphrase)
            } else {
                super.getPageTitle(position)!!
            }
        }

    }

}