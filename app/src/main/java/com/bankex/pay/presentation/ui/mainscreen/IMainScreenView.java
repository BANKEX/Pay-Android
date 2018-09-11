package com.bankex.pay.presentation.ui.mainscreen;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * Интерфейс вьюхи для корневой активити {@link MainScreenActivity}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@StateStrategyType(OneExecutionStateStrategy.class)
public interface IMainScreenView extends BaseView {

    /**
     * показываем онбординг
     */
    void showOnboarding();
}
