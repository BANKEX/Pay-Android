package com.elegion.android.ui.features.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.elegion.android.R;
import com.elegion.android.data.model.Feature;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author mikhail.barannikov on 24.07.2017
 */

public class FeatureViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.top_layout)
    View mTopLayout;
    @BindView(R.id.feature_title)
    TextView mFirstName;
    @BindView(R.id.feature_description)
    TextView mLastName;

    private Feature mFeature;

    public FeatureViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Feature feature) {
        mFeature = feature;
        mFirstName.setText(mFeature.getTitle());
        mLastName.setText(mFeature.getDescription());
    }

}