package com.bankex.pay.presentation.ui.lockscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.BankexPayApplication;
import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.base.BaseActivity;
import com.bankex.pay.presentation.ui.onboarding.OnboardingActivity;
import com.elegion.littlefinger.LittleFinger;

import dagger.android.AndroidInjection;

public class LockScreenActivity extends BaseActivity implements LockScreenView {

    /**
     * Возвращает интент LockScreenActivity
     *
     * @param context Context
     * @return intent
     */
    public static Intent newIntent(Context context) {
        return new Intent(context, LockScreenActivity.class);
    }

    @InjectPresenter
    LockScreenPresenter presenter;

    @ProvidePresenter
    LockScreenPresenter providePresenter() {
        return new LockScreenPresenter(new LittleFinger(BankexPayApplication.getInstance().getApplicationContext()));
    }

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);
        editText = findViewById(R.id.pin);
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
