package com.elegion.android.ui.base.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.pchela.android.data.model.Review;

/**
 * Created by barannikov mikhail
 */

public abstract class AbstractReviewsAdapter<H extends RecyclerView.ViewHolder> extends AbstractPaginationAdapter<H> {

    public AbstractReviewsAdapter() {
    }

    public AbstractReviewsAdapter(@Nullable Callback callback) {
        super(callback);
    }

    public AbstractReviewsAdapter(int loadOffset, @Nullable Callback callback) {
        super(loadOffset, callback);
    }

    public interface OnReviewClickListener {
        void onFooterClick();
        void onReviewClick(Review review);
    }
}
