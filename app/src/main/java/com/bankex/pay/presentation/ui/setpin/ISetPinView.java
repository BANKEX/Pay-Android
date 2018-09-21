package com.bankex.pay.presentation.ui.setpin;

import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * Интерфейс для вью установки пина
 *
 * @author Denis Anisimov.
 */
public interface ISetPinView extends BaseView {

    /**
     * Метод установки пин кода
     */
    void setPin();

    /***
     * Отображение сообщение об ошибке
     * @param message
     */
    void showMessage(String message);


    /**
     * Отображение состояние сенсора
     * @param messageRes
     */
    void setSensorStateMessage(int messageRes);
}
