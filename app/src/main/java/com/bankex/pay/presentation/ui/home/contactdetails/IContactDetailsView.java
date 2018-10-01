package com.bankex.pay.presentation.ui.home.contactdetails;

import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * @author Denis Anisimov.
 */
public interface IContactDetailsView extends BaseView {
    /**
     * Метод отправлябщий
     */
    void sendToAddress();

    /**
     * Метод сохраняющий контакт
     */
    void deleteContact();
}
