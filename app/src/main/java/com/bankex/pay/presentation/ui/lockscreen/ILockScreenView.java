package com.bankex.pay.presentation.ui.lockscreen;

import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * Интерфейс для вью блокировки экрана
 *
 * @author Denis Anisimov.
 */
public interface ILockScreenView extends BaseView {

    /**
     * Метод снятия блокировки с экрана
     */
    void unlock();

    /***
     * Отображение сообщение об ошибке
     * @param message
     */
    void showMessage(int message);

    /**
     * Отображение состояние сенсора
     * @param messageRes
     */
    void setSensorStateMessage(int messageRes);
}
