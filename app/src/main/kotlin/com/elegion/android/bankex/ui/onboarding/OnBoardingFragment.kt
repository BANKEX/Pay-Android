package com.elegion.android.bankex.ui.onboarding

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.bankex.R
import com.elegion.android.bankex.data.Repository
import com.elegion.android.bankex.ui.base.fragment.BaseFragment
import com.elegion.android.bankex.ui.startscreen.StartActivity
import kotlinx.android.synthetic.main.fr_onboarding.*

class OnBoardingFragment : BaseFragment(), OnBoardingView {

    @InjectPresenter
    internal lateinit var presenter: OnBoardingPresenter

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {
            pageIndicatorView.selection = position
            if (position == 2) presenter.changeLabel()
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    @ProvidePresenter
    internal fun providePresenter(): OnBoardingPresenter = OnBoardingPresenter(Repository.get(activity!!))

    override fun getLayout(): Int = R.layout.fr_onboarding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewPager.adapter = OnBoardingPagerAdapter(fragmentManager, onboardings)
    }

    override fun onResume() {
        super.onResume()
        viewPager.addOnPageChangeListener(onPageChangeListener)
        pageButton.setOnClickListener { v ->
            val childCount = viewPager.childCount
            val currentItem = viewPager.currentItem
            presenter.onBoardingNext(currentItem, childCount)
        }
    }

    override fun onPause() {
        super.onPause()
        viewPager.removeOnPageChangeListener(onPageChangeListener)
        pageButton.setOnClickListener(null)
    }

    override fun onBoardingSetLabelStart() {
        pageButton.setText(R.string.btn_start)
    }

    override fun onBoardingNext(currentItem: Int) {
        viewPager.setCurrentItem(currentItem + 1, true)
    }

    override fun onBoardingConfirmed() {
        startActivity(StartActivity.makeIntent(activity!!))
    }

    override fun onBoardingSkipped() {}

    override fun showNoInternetStub() {}

    override fun hideNoInternetStub() {}

    companion object {
        fun newInstance() = OnBoardingFragment()
        val favourites = Onboarding(R.drawable.x35, R.string.favorite_list, R.string.add_your_contacts)
        val standart = Onboarding(R.drawable.x36, R.string.ERC20_standart, R.string.support_any_tokens)
        val network = Onboarding(R.drawable.x37, R.string.custom_network, R.string.add_your_network)
        val onboardings = arrayListOf(favourites, standart, network)
    }
}

