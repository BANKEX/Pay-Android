package com.bankex.pay.presentation.ui.setpin;

import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * Интерфейс для вью установки пина
 *
 * @author Denis Anisimov.
 */
public interface ISetPinView extends BaseView {
    void setPin();

    void showMessage(String message);

    void setSensorStateMessage(int messageRes);
}
