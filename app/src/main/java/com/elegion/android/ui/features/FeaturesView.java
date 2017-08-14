package com.elegion.android.ui.features;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.elegion.android.data.model.Feature;
import com.elegion.android.ui.base.view.ErrorView;
import com.elegion.android.ui.base.view.LoadingView;
import com.elegion.android.ui.base.view.NoInternetStubView;

import java.util.List;

/**
 * @author mikhail.barannikov on 24.07.2017
 */
interface FeaturesView extends LoadingView, NoInternetStubView, ErrorView, MvpView {
    @StateStrategyType(value = AddToEndStrategy.class)
    void showFeatures(List<Feature> features, boolean clear);
    @StateStrategyType(value = SingleStateStrategy.class)
    void clearFeatures();
}
