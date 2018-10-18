package com.bankex.pay.presentation.ui.lockscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.lockscreen.LockScreenInjector;
import com.bankex.pay.presentation.presenter.lockscreen.LockScreenPresenter;
import com.bankex.pay.presentation.ui.view.base.BaseActivity;

import javax.inject.Inject;

/**
 * Активити блокировки приложения
 *
 * @author Denis Anisimov on 13.09.2018.
 */
public class LockScreenActivity extends BaseActivity implements ILockScreenView {

    @Inject
    @InjectPresenter
    LockScreenPresenter presenter;

    @ProvidePresenter
    LockScreenPresenter providePresenter() {
        return presenter;
    }

    private EditText editText;

    /**
     * Возвращает интент LockScreenActivity
     *
     * @param context Context
     * @return intent
     */
    public static Intent newIntent(Context context) {
        return new Intent(context, LockScreenActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LockScreenInjector.getLockScreenComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        editText = findViewById(R.id.et_pin);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.dispatchOnResume();
        editText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                presenter.onAuthByPinClick(editText.getText().toString());
            }
            return false;
        });
    }

    @Override
    protected void onPause() {
        presenter.dispatchOnPause();
        editText.setOnKeyListener(null);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LockScreenInjector.clearLockScreenComponent();
    }

    @Override
    public void unlock() {
        finish();
    }

    @Override
    public void showMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSensorStateMessage(int messageRes) {

    }
}
