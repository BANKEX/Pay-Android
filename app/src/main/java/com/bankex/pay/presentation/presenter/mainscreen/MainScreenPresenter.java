package com.bankex.pay.presentation.presenter.mainscreen;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.mainscreen.IMainScreenView;
import com.bankex.pay.presentation.ui.mainscreen.MainScreenActivity;

/**
 * Презентер корневой активити {@link MainScreenActivity}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
@InjectViewState
public class MainScreenPresenter extends BasePresenter<IMainScreenView> {

    /**
     * Проверяем статус - показывали ли онбординг
     *
     * @param status cтатус boolean
     */
    public void checkOnboardingStatus(boolean status) {
        if (!status) {
            getViewState().showOnboarding();
        } else {
            getViewState().showLockScreen();
        }
    }
}
