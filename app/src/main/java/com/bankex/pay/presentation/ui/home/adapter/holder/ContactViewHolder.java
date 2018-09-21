package com.bankex.pay.presentation.ui.home.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bankex.pay.R;
import com.pkmmte.view.CircularImageView;

/**
 * Холдер для контактов
 *
 * @author Denis Anisimov.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout container;

    private TextView index;

    private TextView name;

    private CircularImageView thumbnail;

    public ContactViewHolder(View itemView) {
        super(itemView);
        container = itemView.findViewById(R.id.container);
        index = itemView.findViewById(R.id.textView_firstLetter);
        name = itemView.findViewById(R.id.textView_contactName);
        thumbnail = itemView.findViewById(R.id.circularView_thumbnail);
    }

}
