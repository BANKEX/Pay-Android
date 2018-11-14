package com.bankex.pay.presentation.navigation.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;

/**
 * Basic navigation router.
 */
public abstract class BaseRouter {
	private static final long FADE_DEFAULT_TIME = 100;

	/**
	 * Method to start the Activity.
	 *
	 * @param context Context
	 * @param intent Intent
	 */
	void startActivity(Context context, Intent intent) {
		context.startActivity(intent);
	}

	/**
	 * Method to run the fragment.
	 *
	 * @param activity activity that holds fragment
	 * @param fragment fragment that will be run
	 * @param containerViewId id of container that holds fragment
	 */
	void runFragment(FragmentActivity activity, Fragment fragment, @IdRes int containerViewId) {
		activity.getSupportFragmentManager()
				.beginTransaction()
				.replace(containerViewId, fragment)
				.commit();
	}

	protected void runFragmentWithAnimation(FragmentActivity activity, Fragment baseFragment, @IdRes int containerViewId) {
		FragmentManager mFragmentManager = activity.getSupportFragmentManager();
		Fragment mPreviousFragment = mFragmentManager.findFragmentById(containerViewId);

		if (mPreviousFragment != null) {
			if (mPreviousFragment.getId() == baseFragment.getId()) {
				return;
			}

			FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

			Fade exitFade = new Fade();
			exitFade.setDuration(FADE_DEFAULT_TIME);
			mPreviousFragment.setExitTransition(exitFade);

			Fade enterFade = new Fade();
			enterFade.setDuration(FADE_DEFAULT_TIME);
			baseFragment.setEnterTransition(enterFade);

			fragmentTransaction
					.replace(containerViewId, baseFragment)
					.addToBackStack(null)
					.commit();
		}
	}

	/**
	 * Method to go back in fragment back stack.
	 *
	 * @param activity activity that holds fragment
	 */
	public void popBackStack(FragmentActivity activity) {
		activity.getSupportFragmentManager().popBackStack();
	}
}
