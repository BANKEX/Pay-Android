package com.bankex.pay.presentation.presenter.lockscreen;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.R;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.ui.lockscreen.LockScreenActivity;
import com.bankex.pay.presentation.ui.lockscreen.ILockScreenView;

import com.bankex.pay.utils.preferences.SharedPreferencesUtils;
import com.elegion.littlefinger.LittleFinger;
import com.elegion.littlefinger.crypto.CryptoAlgorithm;
import com.elegion.littlefinger.fingerprint.AuthResult;

import static io.fabric.sdk.android.Fabric.TAG;

/**
 * Презентер активити блокировки экрана {@link LockScreenActivity}
 *
 * @author Denis Anisimov.
 */
@InjectViewState
public class LockScreenPresenter extends BasePresenter<ILockScreenView> {

    public final static String KEY_ALIAS = "KEY_ALIAS";
    private LittleFinger littleFinger;

    public LockScreenPresenter(LittleFinger littleFinger) {
		this.littleFinger = littleFinger;
    }

    /**
     * Авторизация по пин коду
     * @param pin
     */
    public void onAuthByPinClick(String pin) {
        if (checkIsPinCorrect(pin)) getViewState().unlock();
        else getViewState().showMessage(R.string.pins_don_t_);
    }

    /**
     * Считываение отпечатка
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
     * Отмена считывания отпечатка
     */
    public void dispatchOnPause() {
        littleFinger.cancelAuth();
    }

    private void handleCallback(AuthResult result) {
        switch (result.getState()) {
            case SUCCESS:
                if (checkIsPinCorrect(result.getData())) getViewState().unlock();
                else getViewState().showMessage(R.string.fp_touch_failed);
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

    private boolean checkIsPinCorrect(String data) {
        return data.equals(SharedPreferencesUtils.pin());
    }
}
