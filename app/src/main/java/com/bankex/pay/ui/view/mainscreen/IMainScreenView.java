package com.bankex.pay.ui.view.mainscreen;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.ui.view.base.BaseView;

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

    /**
     * Открываем экран импорта/создания кошелька
     */
    void openImportOrCreate();
}
