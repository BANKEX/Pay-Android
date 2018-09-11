package com.bankex.pay.presentation.ui.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;

import com.bankex.pay.presentation.ui.base.BaseFragment;

/**
 * Базовый роутер для навигации между экранами
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class BaseRouter {

    /**
     * Стартуем активити
     *
     * @param context Context
     * @param intent  Intent
     */
    void startActivity(Context context,
                       Intent intent) {
        context.startActivity(intent);
    }

    /**
     * Запускаем фрагмент
     *
     * @param activity        Activity
     * @param fragment        Fragment, который необходимо запустить
     * @param containerViewId Id контейнера
     */
    public void runFragment(FragmentActivity activity,
                            BaseFragment fragment,
                            @IdRes int containerViewId) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment)
                .commit();
    }
}
