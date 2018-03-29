package com.elegion.android.template.ui.base.fragment

import android.os.Bundle
import android.view.View
import com.elegion.android.template.R
import com.elegion.android.template.ui.base.view.NoInternetStubView
import com.elegion.android.template.util.ViewUtils
import kotlinx.android.synthetic.main.w_no_internet_view.*

abstract class BaseNoInternetFragment : BaseFragment(), NoInternetStubView {
    private val mTryAgainListener = View.OnClickListener { tryAgain() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        noInternetView.bindEmptyView(R.drawable.ic_no_network, R.string.no_internet, R.string.try_again, null)
    }

    override fun onResume() {
        super.onResume()
        noInternetView!!.setButtonClickListener(mTryAgainListener)
    }

    override fun showNetworkError() {
        if (shouldShowNoInternetStubView()) {
            showNoInternetStub()
        } else {
            super.showNetworkError()
        }
    }

    protected open fun shouldShowNoInternetStubView(): Boolean = true

    override fun onPause() {
        super.onPause()
        noInternetView.setButtonClickListener(null)
    }

    override fun hideNoInternetStub() {
        ViewUtils.setVisibility(View.GONE, *getNoInternetViews())
        ViewUtils.setVisibility(View.VISIBLE, *getViews())
    }

    override fun showNoInternetStub() {
        ViewUtils.setVisibility(View.INVISIBLE, *getViews())
        ViewUtils.setVisibility(View.VISIBLE, *getNoInternetViews())
    }

    protected fun getNoInternetViews(): Array<View> = arrayOf(noInternetView)

    protected abstract fun getViews(): Array<View>

    abstract fun tryAgain()
}
