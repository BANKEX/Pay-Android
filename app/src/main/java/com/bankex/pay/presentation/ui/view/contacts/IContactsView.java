package com.bankex.pay.presentation.ui.view.contacts;

import com.bankex.pay.presentation.ui.view.base.BaseView;

/**
 * Вью экрана контактов
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
public interface IContactsView extends BaseView {
	/**
	 * Тестовый метод
	 */
	void showToast();

	/**
	 * Method that shows contacts list recycler and hides empty view
	 */
	void showContactsList();

	/**
	 * Method that hides contacts list recycler and shows empty view
	 */
	void hideContactsList();
}
