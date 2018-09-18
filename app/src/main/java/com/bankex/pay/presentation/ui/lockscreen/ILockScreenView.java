package com.bankex.pay.presentation.ui.lockscreen;

import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * Интерфейс для вью блокировки экрана
 *
 * @author Denis Anisimov.
 */
public interface ILockScreenView extends BaseView {
    void unlock();

    void showMessage(int message);

    void setSensorStateMessage(int messageRes);
}
