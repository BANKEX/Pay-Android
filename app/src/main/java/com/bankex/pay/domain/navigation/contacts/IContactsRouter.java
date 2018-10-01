package com.bankex.pay.domain.navigation.contacts;

import com.bankex.pay.presentation.ui.base.BaseFragment;

/**
 * @author Denis Anisimov.
 */
public interface IContactsRouter {

    /**
     * Метод открывает фрагмент добавления контакта
     * @param fragment
     */
    void goToAddContacts(BaseFragment fragment);

    /**
     * Метод открывает фрагмент деталей контакта
     * @param fragment
     */
    void goToContactDetails(BaseFragment fragment);
}
