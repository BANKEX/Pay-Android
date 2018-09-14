package com.bankex.pay.presentation.ui.lockscreen;

import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * @author Denis Anisimov.
 */
public interface LockScreenView extends BaseView {
    void unlock();

    void showMessage(int message);

    void setSensorStateMessage(int messageRes);
}
