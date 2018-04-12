package com.elegion.android.template.ui.features.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.elegion.android.template.data.model.Feature
import kotlinx.android.synthetic.main.li_feature.view.*

class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(feature: Feature) {
        feature.apply {
            itemView.featureTitle.text = title
            itemView.featureDescription.text = description
        }
    }
}