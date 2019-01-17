package com.bankex.pay.presentation.presenter;

import android.util.Log;
import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.R;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.lockscreen.ILockScreenView;
import com.bankex.pay.presentation.ui.lockscreen.LockScreenActivity;
import com.bankex.pay.utils.preferences.SharedPreferencesUtils;
import com.elegion.littlefinger.LittleFinger;
import com.elegion.littlefinger.crypto.CryptoAlgorithm;
import com.elegion.littlefinger.fingerprint.AuthResult;

import static io.fabric.sdk.android.Fabric.TAG;

/**
 * Presenter for {@link LockScreenActivity}.
 */
@InjectViewState
public class LockScreenPresenter extends BasePresenter<ILockScreenView> {
	private final static String KEY_ALIAS = "KEY_ALIAS";
	private LittleFinger littleFinger;

	public LockScreenPresenter(LittleFinger littleFinger) {
		this.littleFinger = littleFinger;
	}

	/**
	 * Method to authorize by pin.
	 *
	 * @param pin entered pin
	 */
	public void onAuthByPinClick(String pin) {
		if (checkIfPinCorrect(pin)) {
			getViewState().unlock();
		} else {
			getViewState().showMessage(R.string.pins_do_not_match);
		}
	}

	/**
	 * Method to read fingerprint.
	 */
	public void dispatchOnResume() {
		if (littleFinger.isReadyToUse() && SharedPreferencesUtils.isPinEncoded()) {
			String encoded = SharedPreferencesUtils.encodedPin();
			littleFinger.decode(encoded, KEY_ALIAS, CryptoAlgorithm.RSA, this::handleCallback);
		} else {
			getViewState().setSensorStateMessage(R.string.fp_auth_by_fp_unavailable);
		}
	}

	/**
	 * Method to cancel fingerprint reading.
	 */
	public void dispatchOnPause() {
		littleFinger.cancelAuth();
	}

	/**
	 * Callback to handle fingerprint decoding.
	 *
	 * @param result decoding result
	 */
	private void handleCallback(AuthResult result) {
		switch (result.getState()) {
			case SUCCESS:
				if (checkIfPinCorrect(result.getData())) {
					getViewState().unlock();
				} else {
					getViewState().showMessage(R.string.fp_touch_failed);
				}
				break;
			case HELP:
				getViewState().showMessage(R.string.fp_touch_help);
			case ERROR:
				getViewState().showMessage(R.string.fp_touch_failed);
			case EXCEPTION: {
				if (result.isKeyInvalidated()) {
					getViewState().setSensorStateMessage(R.string.fp_added_or_removed_fp);
					SharedPreferencesUtils.setEncodedPin(null);
				} else {
					getViewState().showMessage(R.string.fp_auth_by_fp_unavailable);
				}
				Log.d(TAG, "exc", result.getThrowable());
			}
		}
	}

	private boolean checkIfPinCorrect(String data) {
		return data.equals(SharedPreferencesUtils.pin());
	}
}
