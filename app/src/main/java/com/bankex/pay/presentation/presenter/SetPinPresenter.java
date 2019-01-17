package com.bankex.pay.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.R;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.setpin.ISetPinView;
import com.bankex.pay.presentation.ui.setpin.SetPinActivity;
import com.bankex.pay.utils.preferences.SharedPreferencesUtils;
import com.elegion.littlefinger.LittleFinger;
import com.elegion.littlefinger.crypto.CryptoAlgorithm;
import com.elegion.littlefinger.fingerprint.AuthResult;

/**
 * Presenter for {@link SetPinActivity}.
 */
@InjectViewState
public class SetPinPresenter extends BasePresenter<ISetPinView> {
	private final static String KEY_ALIAS = "KEY_ALIAS";
	private LittleFinger littleFinger;

	public SetPinPresenter(LittleFinger littleFinger) {
		this.littleFinger = littleFinger;
	}

	/**
	 * Method to save encrypted and not encripted pin code.
	 *
	 * @param pin enter pin code
	 */
	public void savePin(String pin) {
		SharedPreferencesUtils.setPin(pin);
		if (littleFinger.isReadyToUse()) {
			littleFinger.encode(pin, KEY_ALIAS, CryptoAlgorithm.RSA, this::handleResult);
		} else {
			getViewState().setSensorStateMessage(R.string.fp_sensor_not_supported);
		}
	}

	/**
	 * Callback to handle fingerprint decoding.
	 *
	 * @param authResult encoding result
	 */
	private void handleResult(AuthResult authResult) {
		switch (authResult.getState()) {
			case SUCCESS: {
				String encoded = authResult.getData();
				SharedPreferencesUtils.setEncodedPin(encoded);
				getViewState().setPin();
				break;
			}
			default: {
				Throwable throwable = authResult.getThrowable();
				if (throwable != null) {
					getViewState().showMessage(throwable.getMessage());
				}
			}
		}
	}
}
