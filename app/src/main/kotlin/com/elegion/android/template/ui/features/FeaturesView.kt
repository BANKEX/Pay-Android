package com.elegion.android.template.ui.features

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.elegion.android.template.data.model.Feature
import com.elegion.android.template.ui.base.view.ErrorView
import com.elegion.android.template.ui.base.view.LoadingView
import com.elegion.android.template.ui.base.view.NoInternetStubView

internal interface FeaturesView : LoadingView, NoInternetStubView, ErrorView, MvpView {
    @StateStrategyType(value = AddToEndStrategy::class)
    fun showFeatures(features: List<Feature>, clear: Boolean)

    @StateStrategyType(value = SingleStateStrategy::class)
    fun clearFeatures()
}
