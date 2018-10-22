package com.bankex.pay.ui.presenter.setpin;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.R;
import com.bankex.pay.ui.presenter.base.BasePresenter;
import com.bankex.pay.ui.presenter.lockscreen.LockScreenPresenter;
import com.bankex.pay.ui.view.setpin.ISetPinView;
import com.bankex.pay.ui.view.setpin.SetPinActivity;
import com.bankex.pay.utils.preferences.SharedPreferencesUtils;
import com.elegion.littlefinger.LittleFinger;
import com.elegion.littlefinger.crypto.CryptoAlgorithm;
import com.elegion.littlefinger.fingerprint.AuthResult;

/**
 * Presenter for pin code setting screen {@link SetPinActivity}
 *
 * @author Denis Anisimov.
 */
@InjectViewState
public class SetPinPresenter extends BasePresenter<ISetPinView> {
	private LittleFinger littleFinger;

	public SetPinPresenter(LittleFinger littleFinger) {
		this.littleFinger = littleFinger;
	}

	/**
	 * Method for saving coded pin code.
	 * If phone does`t have sensor, method will be skipped.
	 *
	 * @param pin - entered pin code
	 */
	public void savePin(String pin) {
		SharedPreferencesUtils.setPin(pin);

		if (littleFinger.isReadyToUse()) {
			littleFinger.encode(pin, LockScreenPresenter.KEY_ALIAS, CryptoAlgorithm.RSA, this::handleResult);
		} else {
			getViewState().setSensorStateMessage(R.string.fp_sensor_not_supported);
		}
	}

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
