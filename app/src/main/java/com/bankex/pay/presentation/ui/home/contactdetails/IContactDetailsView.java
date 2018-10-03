package com.bankex.pay.presentation.ui.home.contactdetails;

import com.bankex.pay.presentation.ui.view.base.BaseView;

/**
 * Интерфейс вьюхи просмотра деталей контакта
 *
 * @author Denis Anisimov.
 */
public interface IContactDetailsView extends BaseView {
    /**
     * Метод отправлябщий средства на адрес
     */
    void sendToAddress();

    /**
     * Метод удаляющий контакт
     */
    void deleteContact();
}
