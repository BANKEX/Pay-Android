package com.bankex.pay.presentation.view.setpin;

import com.bankex.pay.presentation.view.base.BaseView;

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
