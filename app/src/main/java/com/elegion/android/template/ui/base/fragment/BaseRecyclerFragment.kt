package com.elegion.android.template.ui.base.fragment

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elegion.android.template.R
import kotlinx.android.synthetic.main.fr_recycler.*

abstract class BaseRecyclerFragment : BaseRefresherFragment() {
    open val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    override fun shouldShowNoInternetStubView(): Boolean = recycler.adapter.itemCount == 0

    @LayoutRes
    override fun getLayout(): Int = R.layout.fr_recycler

    @CallSuper
    protected fun initRecycler() {
        recycler.layoutManager = layoutManager
        recycler.adapter = getAdapter()
    }

    protected abstract fun getAdapter(): RecyclerView.Adapter<*>

    protected fun bindEmptyView(@DrawableRes iconRes: Int, @StringRes msgTextRes: Int,
                                @StringRes buttonTextRes: Int, listener: View.OnClickListener) =
            emptyView.bindEmptyView(iconRes, msgTextRes, buttonTextRes, listener)

    protected fun bindEmptyView(@DrawableRes iconRes: Int, @StringRes msgTextRes: Int) =
            emptyView.bindEmptyView(iconRes, msgTextRes)

    fun bindEmptyView(@StringRes text: Int) = emptyView.bindEmptyView(text)

    override fun getViews(): Array<View> = arrayOf(recycler)
}
