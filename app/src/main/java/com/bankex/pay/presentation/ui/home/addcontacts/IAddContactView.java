package com.bankex.pay.presentation.ui.home.addcontacts;

import com.bankex.pay.presentation.ui.view.base.BaseView;

/**
 * @author Denis Anisimov.
 */
public interface IAddContactView extends BaseView {

    /**
     * Метод копирующий адресс кошелька из клипборда
     */
    void pasteAddress();

    /**
     * Метод сохраняющий контакт
     */
    void saveContact();

}
