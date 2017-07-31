package com.elegion.android.ui.features.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.android.R;
import com.elegion.android.data.model.Feature;
import com.elegion.android.ui.base.adapter.AbstractPaginationAdapter;
import com.elegion.android.ui.features.adapter.holder.FeatureViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mikhail.barannikov on 24.07.2017
 */

public class FeaturesAdapter extends AbstractPaginationAdapter<FeatureViewHolder> {
    private final List<Feature> mItems = new ArrayList<>();

    @Override
    public FeatureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_feature, parent, false);
        return new FeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeatureViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setData(List<Feature> items, boolean clear) {
        if (clear) {
            mItems.clear();
        }
        mItems.addAll(items);
        notifyDataSetChanged();
    }
}
