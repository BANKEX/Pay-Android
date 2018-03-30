package com.elegion.android.template.ui.base.adapter

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView

abstract class AbstractPaginationAdapter<H : RecyclerView.ViewHolder> @JvmOverloads constructor(
        loadOffset: Int = LOAD_OFFSET
) : RecyclerView.Adapter<H>() {

    var callback: Callback? = null
    protected var loadOffset = LOAD_OFFSET
    protected var paginationEnabled = true

    init {
        this.loadOffset = loadOffset
    }

    constructor(callback: Callback) : this(LOAD_OFFSET) {
        this.callback = callback
    }

    constructor(loadOffset: Int, callback: Callback) : this(loadOffset) {
        this.callback = callback
    }

    @CallSuper
    override fun onBindViewHolder(holder: H, position: Int) {
        if (paginationEnabled && position == itemCount - loadOffset) {
            callback?.loadMore()
        }
    }

    interface Callback {
        fun loadMore()
    }

    companion object {
        const val LOAD_OFFSET = 4
    }
}
