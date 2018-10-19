package com.bankex.pay.presentation.view.mainscreen;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.presentation.view.base.BaseView;

/**
 * Interface for main activity view {@link MainScreenActivity}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@StateStrategyType(OneExecutionStateStrategy.class)
public interface IMainScreenView extends BaseView {

    /**
     * Method to show onboarding
     */
    void showOnboarding();

    /**
     * Method to show lock screen
     */
    void showLockScreen();
}
