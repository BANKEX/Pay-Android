package com.elegion.android.template.ui.splash;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * @author mikhail.barannikov on 04.08.2017
 */
interface SplashView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void openLogin();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openFeatures();
}
