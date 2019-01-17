package com.bankex.pay.presentation.ui.base;

import android.content.Context;
import android.os.IBinder;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.bankex.pay.R;
import com.bankex.pay.presentation.navigation.base.IBankexRouter;
import javax.inject.Inject;

/**
 * Basic application Activity.
 */
public abstract class BaseActivity extends MvpAppCompatActivity {
	@Inject
	IBankexRouter mRouter;

	/**
	 * Method to run fragment with animation.
	 *
	 * @param baseFragment fragment that extends BaseFragment
	 */
	public void runFragmentWithAnimation(BaseFragment baseFragment) {
		mRouter.runFragmentWithAnimation(this, baseFragment, R.id.fragment_container);
	}

	/**
	 * Method to run fragment.
	 *
	 * @param fragment fragment that extends BaseFragment
	 */
	public void runFragment(BaseFragment fragment) {
		mRouter.runBankexFragment(this, fragment, R.id.fragment_container);
	}

	/**
	 * Method to hide keyboard.
	 */
	public void hideKeyboard() {
		FragmentActivity activity = this;
		final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

		View view = activity.getCurrentFocus();
		IBinder binder = null;
		if (view != null) {
			binder = view.getWindowToken();
		} else if (activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
			binder = activity.getWindow().getDecorView().getWindowToken();
		}
		if (binder == null) {
			return;
		}
		try {
			imm.hideSoftInputFromWindow(binder, 0);
		} catch (Exception ignored) {
			//do nothing
		}
	}

	/**
	 * Method to get string by its` id.
	 *
	 * @param resId int string id from resources.
	 * @return String from resources.
	 */
	public String string(@StringRes int resId) {
		return getResources().getString(resId);
	}
}
