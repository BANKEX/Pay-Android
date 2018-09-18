package com.bankex.pay.presentation.presenter.setpin;

import com.arellomobile.mvp.InjectViewState;
import com.bankex.pay.R;
import com.bankex.pay.presentation.presenter.base.BasePresenter;
import com.bankex.pay.presentation.presenter.lockscreen.LockScreenPresenter;
import com.bankex.pay.presentation.ui.setpin.ISetPinView;
import com.bankex.pay.utils.SharedPreferencesUtils;
import com.elegion.littlefinger.LittleFinger;
import com.elegion.littlefinger.crypto.CryptoAlgorithm;
import com.elegion.littlefinger.fingerprint.AuthResult;

/**
 * Презентер активити экрана установки пин кодж{@link com.bankex.pay.presentation.ui.setpin.SetPinActivity}
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
     * Метод сохранения пин кода зашифрованного и нет
     *
     * @param pin - введённый пин код
     */
    public void savePin(String pin) {
        SharedPreferencesUtils.setPin(pin);
        if (littleFinger.isReadyToUse()) {
            littleFinger.encode(pin, LockScreenPresenter.KEY_ALIAS, CryptoAlgorithm.RSA, this::handleResult);
        } else getViewState().setSensorStateMessage(R.string.fp_sensor_not_supported);
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
