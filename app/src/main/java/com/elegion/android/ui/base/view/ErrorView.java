package com.elegion.android.ui.base.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * @author Mikhail Barannikov
 */
@StateStrategyType(OneExecutionStateStrategy.class)
public interface ErrorView {

    void showNetworkError();

    void showUnexpectedError();

    void showErrorMessage(@NonNull String message);

    void showErrorMessage(@StringRes int message);

    void hideErrorMessage();
}
