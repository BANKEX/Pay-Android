package com.bankex.pay.presentation.ui.navigation.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.transition.Fade;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Базовый роутер для навигации между экранами
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class BaseRouter {
	private static final long FADE_DEFAULT_TIME = 100;

	/**
	 * Стартуем активити
	 *
	 * @param context Context
	 * @param intent Intent
	 */
	void startActivity(Context context, Intent intent) {
		context.startActivity(intent);
	}

	/**
	 * Запускаем фрагмент
	 *
	 * @param activity Activity
	 * @param fragment Fragment, который необходимо запустить
	 * @param containerViewId Id контейнера
	 */
	void runFragment(FragmentActivity activity,
			Fragment fragment,
			@IdRes int containerViewId) {
		activity.getSupportFragmentManager()
				.beginTransaction()
				.replace(containerViewId, fragment)
				.commit();
	}

	protected void runFragmentWithAnimation(FragmentActivity activity,
			Fragment baseFragment,
			@IdRes int containerViewId) {
		FragmentManager mFragmentManager = activity.getSupportFragmentManager();
		Fragment previousFragment = mFragmentManager.findFragmentById(containerViewId);

		if (previousFragment.getId() == baseFragment.getId()) {
			return;
		}

		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

		// 1. Exit for Previous Fragment
		Fade exitFade = new Fade();
		exitFade.setDuration(FADE_DEFAULT_TIME);
		previousFragment.setExitTransition(exitFade);

		// 2. Enter for New Fragment
		Fade enterFade = new Fade();
		enterFade.setDuration(FADE_DEFAULT_TIME);
		baseFragment.setEnterTransition(enterFade);

		fragmentTransaction.replace(containerViewId,
				baseFragment);
		fragmentTransaction.commit();
	}
}
