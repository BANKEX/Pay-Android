package com.bankex.pay.presentation.ui.navigation.contacts;


import com.bankex.pay.presentation.ui.view.base.BaseFragment;

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
