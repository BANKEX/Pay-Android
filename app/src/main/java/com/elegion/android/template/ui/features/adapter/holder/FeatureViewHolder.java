package com.elegion.android.template.ui.features.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.elegion.android.template.R;
import com.elegion.android.template.data.model.Feature;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author mikhail.barannikov on 24.07.2017
 */

public class FeatureViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.feature_title)
    TextView mFeatureTitle;
    @BindView(R.id.feature_description)
    TextView mFeatureDescription;

    private Feature mFeature;

    public FeatureViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Feature feature) {
        mFeature = feature;
        mFeatureTitle.setText(mFeature.getTitle());
        mFeatureDescription.setText(mFeature.getDescription());
    }

}