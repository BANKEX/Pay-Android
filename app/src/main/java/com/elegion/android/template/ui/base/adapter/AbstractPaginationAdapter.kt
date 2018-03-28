package com.elegion.android.template.ui.base.adapter

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView

abstract class AbstractPaginationAdapter<H : RecyclerView.ViewHolder> @JvmOverloads constructor(
        loadOffset: Int = LOAD_OFFSET
) : RecyclerView.Adapter<H>() {

    var mCallback: Callback? = null
    protected var mLoadOffset = LOAD_OFFSET
    protected var mPaginationEnabled = true

    init {
        mLoadOffset = loadOffset
    }

    constructor(callback: Callback) : this(LOAD_OFFSET) {
        mCallback = callback
    }

    constructor(loadOffset: Int, callback: Callback) : this(loadOffset) {
        mCallback = callback
    }

    @CallSuper
    override fun onBindViewHolder(holder: H, position: Int) {
        if (mPaginationEnabled && position == itemCount - mLoadOffset) {
            mCallback?.loadMore()
        }
    }

    interface Callback {
        fun loadMore()
    }

    companion object {
        const val LOAD_OFFSET = 4
    }
}
