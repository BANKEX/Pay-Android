package com.elegion.android.ui.login;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.elegion.android.ui.base.view.ErrorView;
import com.elegion.android.ui.base.view.LoadingView;
import com.elegion.android.ui.base.view.NoInternetStubView;

/**
 * @author Mike
 */

interface LoginView extends LoadingView, NoInternetStubView, ErrorView, MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void loginSuccessful();
}
