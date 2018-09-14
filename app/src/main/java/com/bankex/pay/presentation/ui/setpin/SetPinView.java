package com.bankex.pay.presentation.ui.setpin;

import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * @author Denis Anisimov.
 */
public interface SetPinView extends BaseView{
    void setPin();

    void showMessage(String message);

    void setSensorStateMessage(int messageRes);
}
