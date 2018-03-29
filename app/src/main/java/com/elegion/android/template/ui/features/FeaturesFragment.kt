package com.elegion.android.template.ui.features

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.elegion.android.template.R
import com.elegion.android.template.data.Repository
import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.ui.base.adapter.AbstractPaginationAdapter
import com.elegion.android.template.ui.base.fragment.BaseRecyclerFragment
import com.elegion.android.template.ui.features.adapter.FeaturesAdapter
import com.elegion.android.template.util.AuthUtils

import timber.log.Timber

class FeaturesFragment : BaseRecyclerFragment(), FeaturesView, AbstractPaginationAdapter.Callback {
    @InjectPresenter
    internal lateinit var mPresenter: FeaturesPresenter

    private val mAdapter = FeaturesAdapter()

    @ProvidePresenter
    internal fun providePresenter(): FeaturesPresenter = FeaturesPresenter(Repository.get(activity!!))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mAdapter.mCallback = this
    }

    override fun getAdapter(): RecyclerView.Adapter<*> = mAdapter

    override fun onRefresh() = mPresenter.loadFeatures(true)

    override fun tryAgain() = mPresenter.loadFeatures(true)

    override fun showFeatures(features: List<Feature>, clear: Boolean) {
        Timber.d("MOXY: showFeatures(%s)", clear)
        mAdapter.setData(features, clear)
    }

    override fun clearFeatures() {
        Timber.d("MOXY: clearFeatures()")
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.logout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.features_menu_logout -> {
            mPresenter.logout()
            AuthUtils.openLogin(activity!!)
            super.onOptionsItemSelected(item)
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun loadMore() = mPresenter.loadFeatures(false)

    companion object {
        fun newInstance(): Fragment {
            return FeaturesFragment()
        }
    }
}
