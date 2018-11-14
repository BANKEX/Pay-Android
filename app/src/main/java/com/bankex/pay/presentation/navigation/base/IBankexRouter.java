package com.bankex.pay.presentation.navigation.base;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import com.bankex.pay.presentation.ui.base.BaseFragment;

/**
 * Interface to navigate between screens.
 */
public interface IBankexRouter {

	/**
	 * Method to open fragment with animation.
	 *
	 * @param activity activity
	 * @param baseFragment fragment that will be run
	 * @param containerViewId id of container that holds fragment
	 */
	void runFragmentWithAnimation(FragmentActivity activity, BaseFragment baseFragment, @IdRes int containerViewId);

	/**
	 * Run fragment.
	 *
	 * @param activity activity
	 * @param fragment fragment that will be tun
	 * @param containerViewId id of container that holds fragment
	 */
	void runBankexFragment(FragmentActivity activity, BaseFragment fragment, @IdRes int containerViewId);
}
