package com.elegion.android.template.ui.base.view;

import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.elegion.android.template.util.moxy.strategy.AddToEndSingleByTagStrategy;
import com.elegion.android.template.util.moxy.strategy.OneExecutionByTagStrategy;

/**
 * @author Mikhail Barannikov
 */
public interface LoadingView {
    @StateStrategyType(value = AddToEndSingleByTagStrategy.class, tag = "LoadingView")
    void showLoadingIndicator();

    @StateStrategyType(value = OneExecutionByTagStrategy.class, tag = "LoadingView")
    void hideLoadingIndicator();

}
