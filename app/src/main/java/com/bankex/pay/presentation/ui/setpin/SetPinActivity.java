package com.bankex.pay.presentation.ui.setpin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bankex.pay.R;
import com.bankex.pay.di.setpin.SetPinInjector;
import com.bankex.pay.presentation.presenter.SetPinPresenter;
import com.bankex.pay.presentation.ui.base.BaseActivity;
import javax.inject.Inject;

/**
 * View for set pin code screen.
 */
public class SetPinActivity extends BaseActivity implements ISetPinView {
	@Inject
	@InjectPresenter
	SetPinPresenter presenter;

	@ProvidePresenter
	SetPinPresenter providePresenter() {
		return presenter;
	}

	private TextView editText;

	public static Intent newIntent(Context context) {
		return new Intent(context, SetPinActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SetPinInjector.getSetPinComponent().inject(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_pin);
		editText = findViewById(R.id.pin);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		SetPinInjector.clearSetPinComponent();
	}

	@Override
	protected void onResume() {
		super.onResume();
		editText.setOnKeyListener((v, keyCode, event) -> {
			if (keyCode == KeyEvent.KEYCODE_ENTER) {
				presenter.savePin(editText.getText().toString());
			}
			return false;
		});
	}

	@Override
	protected void onPause() {
		editText.setOnKeyListener(null);
		super.onPause();
	}

	@Override
	public void setPin() {
		finish();
	}

	@Override
	public void showMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void setSensorStateMessage(@StringRes int messageRes) {
	}
}
