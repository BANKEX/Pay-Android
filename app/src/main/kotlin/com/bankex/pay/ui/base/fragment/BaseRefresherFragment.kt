package com.bankex.pay.ui.base.fragment

import android.support.v4.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.fr_recycler.*
import timber.log.Timber

abstract class BaseRefresherFragment : BaseNoInternetFragment(), SwipeRefreshLayout.OnRefreshListener {
    override fun onResume() {
        super.onResume()
        refresher.setOnRefreshListener(this)
    }

    override fun onPause() {
        refresher.setOnRefreshListener(null)
        super.onPause()
    }

    override fun showLoadingIndicator() {
        Timber.d("MOXY: Refresher showLoadingIndicator()")
        refresher?.post {
            refresher?.isRefreshing = true
        }
    }

    override fun hideLoadingIndicator() {
        Timber.d("MOXY: Refresher hideLoadingIndicator()")
        refresher?.post {
            refresher?.isRefreshing = false
        }
    }
}
