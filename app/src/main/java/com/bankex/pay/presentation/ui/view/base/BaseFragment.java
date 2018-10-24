package com.bankex.pay.presentation.ui.view.base;

import android.content.Context;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.bankex.pay.R;

/**
 * Базовый фрагмент приложения
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class BaseFragment extends MvpAppCompatFragment {

	/**
	 * Возвращаем BottomNavigationView или null
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
	 * Пытаемся спрятать клавиатуру
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
			imm.hideSoftInputFromWindow(binder, 0);
		} catch (Exception ignored) {
			//do nothing
		}
	}

	public void finish() {
		FragmentActivity activity = getActivity();
		if (activity != null) {
			activity.finish();
		}
	}

	/**
	 * Method to show message in Fragment with Toast.
	 *
	 * @param message - message string.
	 */
	public void showMessageToast(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}
}
