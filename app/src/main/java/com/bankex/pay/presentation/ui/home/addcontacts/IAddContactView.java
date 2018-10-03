package com.bankex.pay.presentation.ui.home.addcontacts;

import com.bankex.pay.presentation.ui.view.base.BaseView;

/**
 * Интерфейс вьюхи добавления контакта
 *
 * @author Denis Anisimov.
 */
public interface IAddContactView extends BaseView {

    /**
     * Метод копирующий адресс кошелька из клипборда
     */
    void pasteAddress(String address);

    /**
     * Метод сохраняющий контакт
     */
    void saveContact();

}
