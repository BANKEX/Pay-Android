package com.bankex.pay.presentation.ui.navigation;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;

import com.bankex.pay.presentation.ui.base.BaseFragment;

/**
 * Интерфейс роутера для навигации между экранами
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public interface IBankexRouter {
    /**
     * Запускаем фрагмент с анимацией
     *
     * @param activity        Activity
     * @param baseFragment    Fragment, который необходимо запустить
     * @param containerViewId Id контейнера
     */
    void runFragmentWithAnimation(FragmentActivity activity,
                                  BaseFragment baseFragment,
                                  @IdRes int containerViewId);

    /**
     * Запускаем фрагмент
     *
     * @param activity        Activity
     * @param fragment        Fragment, который необходимо запустить
     * @param containerViewId Id контейнера
     */
    void runBankexFragment(FragmentActivity activity,
                     BaseFragment fragment,
                     @IdRes int containerViewId);


}
