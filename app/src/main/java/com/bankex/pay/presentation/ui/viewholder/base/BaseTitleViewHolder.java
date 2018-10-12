package com.bankex.pay.presentation.ui.viewholder.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bankex.pay.R;

/**
 * ViewHolder view компонента, отображающего базовый заголовок и кнопку добавления
 *
 * @author Pavel Apanovskiy on 13/10/2018.
 */
public class BaseTitleViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitleTextView;
    private TextView mAddTextView;

    public BaseTitleViewHolder(View itemView) {
        super(itemView);
        mTitleTextView = itemView.findViewById(R.id.title_text_view);
        mAddTextView = itemView.findViewById(R.id.add_text_view);
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public TextView getAddTextView() {
        return mAddTextView;
    }
}
