package com.elegion.android.template.ui.features.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.elegion.android.template.R
import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.ui.base.adapter.AbstractPaginationAdapter
import com.elegion.android.template.ui.features.adapter.holder.FeatureViewHolder
import java.util.ArrayList

class FeaturesAdapter : AbstractPaginationAdapter<FeatureViewHolder>() {
    private val items = ArrayList<Feature>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.li_feature, parent, false)
        return FeatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setData(items: List<Feature>, clear: Boolean) {
        if (clear) {
            this.items.clear()
        }
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
