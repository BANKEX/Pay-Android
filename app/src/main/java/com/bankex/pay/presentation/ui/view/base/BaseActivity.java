package com.bankex.pay.presentation.ui.view.base;

import android.content.Context;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.navigation.base.IBankexRouter;

import javax.inject.Inject;

/**
 * Базовый активити приложения
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class BaseActivity extends MvpAppCompatActivity {

    @Inject
    IBankexRouter mRouter;

    /**
     * Запускаем фрагмент с анимацией
     *
     * @param baseFragment BaseFragment
     */
    public void runFragmentWithAnimation(BaseFragment baseFragment) {
        mRouter.runFragmentWithAnimation(this,
                baseFragment,
                R.id.fragment_container);
    }

    /**
     * Запускаем фрагмент
     *
     * @param fragment BaseFragment
     */
    public void runFragment(BaseFragment fragment) {
        mRouter.runBankexFragment(this,
                fragment,
                R.id.fragment_container);
    }

    /**
     * Пытаемся спрятать клавиатуру
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
}
