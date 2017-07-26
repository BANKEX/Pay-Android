package com.elegion.android.ui.base.view;

import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.elegion.android.util.moxy.strategy.AddToEndSingleByTagStrategy;
import com.elegion.android.util.moxy.strategy.SkipByTagStrategy;

/**
 * @author mikhail barannikov
 */
public interface NoInternetStubView {
    @StateStrategyType(value = AddToEndSingleByTagStrategy.class, tag = "NoInternetStubView")
    void showNoInternetStub();

    @StateStrategyType(value = SkipByTagStrategy.class, tag = "NoInternetStubView")
    void hideNoInternetStub();
}
