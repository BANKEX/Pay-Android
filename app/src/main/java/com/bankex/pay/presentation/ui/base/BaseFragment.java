package com.bankex.pay.presentation.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bankex.pay.R;
import com.hannesdorfmann.fragmentargs.FragmentArgs;

/**
 * Base fragment for application.
 * Base functions:
 * - show bottom navigation view;
 * - hide keyboard;
 * - show message Toast;
 * - return String bt its` resId.
 */
public abstract class BaseFragment /*<, P extends Presenter>*/ extends MvpAppCompatFragment {
	protected Unbinder sBinder;

	//@Inject
	//protected P mPresenter;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FragmentArgs.inject(this);
	}

	@Override public void onDestroyView() {
		super.onDestroyView();
		if (this.sBinder != null) {
			this.sBinder.unbind();
		}
	}

	/**
	 * Method to set content view and create ButterKnife binding.
	 *
	 * @param inflater LayoutInflater
	 * @param container ViewGroup
	 * @param layoutResId layout resource id to inflate
	 * @return Inflated view
	 */
	protected final View setAndBindContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @LayoutRes int layoutResId) {
		View view = inflater.inflate(layoutResId, container, false);
		sBinder = ButterKnife.bind(this, view);
		return view;
	}

	/**
	 * Method to return bottom navigation or null.
	 *
	 * @return BottomNavigationView
	 */
	@Nullable
	public BottomNavigationView getBottomNavigationView() {
		BottomNavigationView bottomNavigationView = null;
		if (getActivity() != null) {
			bottomNavigationView = getActivity().findViewById(R.id.home_navigation);
		}
		return bottomNavigationView;
	}

	/**
	 * Method to hide keyboard.
	 */
	public void hideKeyboard() {
		FragmentActivity activity = getActivity();
		if (activity == null) {
			return;
		}
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
			if (imm != null) {
				imm.hideSoftInputFromWindow(binder, 0);
			}
		} catch (Exception ignored) {
			//do nothing
		}
	}

	/**
	 * Method to finish an activity.
	 */
	public void finish() {
		FragmentActivity activity = getActivity();
		if (activity != null) {
			activity.finish();
		}
	}

	/**
	 * Method to show message in Fragment with Toast.
	 *
	 * @param message message string.
	 */
	public void showMessageToast(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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
