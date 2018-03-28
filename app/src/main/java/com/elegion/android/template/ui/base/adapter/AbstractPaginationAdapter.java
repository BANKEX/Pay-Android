package com.elegion.android.template.ui.base.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

public abstract class AbstractPaginationAdapter<H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {
    public static final int LOAD_OFFSET = 4;

    private int mLoadOffset = LOAD_OFFSET;
    private Callback mCallback;
    protected boolean mPaginationEnabled = true;

    public AbstractPaginationAdapter() {
        this(LOAD_OFFSET);
    }

    public AbstractPaginationAdapter(int loadOffset) {
        mLoadOffset = loadOffset;
    }

    public AbstractPaginationAdapter(Callback callback) {
        this(LOAD_OFFSET);
        mCallback = callback;
    }

    public AbstractPaginationAdapter(int loadOffset, @Nullable Callback callback) {
        this(loadOffset);
        mCallback = callback;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    protected void setLoadOffset(int loadOffset) {
        mLoadOffset = loadOffset;
    }

    @Override
    @CallSuper
    public void onBindViewHolder(H holder, int position) {
        if (mPaginationEnabled && position == (getItemCount() - mLoadOffset) && mCallback != null) {
            mCallback.loadMore();
        }
    }

    public interface Callback {
        void loadMore();
    }
}
