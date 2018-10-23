package com.bankex.pay.presentation.ui.view.contacts;

import com.bankex.pay.presentation.ui.view.base.BaseView;

/**
 * Вью экрана контактов
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
public interface IContactsView extends BaseView {

	/**
	 *  Method that shows contacts list recycler
	 *
	 * @param isShow - boolean
	 * if true - shows contacts, otherwise - hides
	 */
	void showContactsList(boolean isShow);

	/**
	 * Method that shows empty view
	 *
	 * @param isShow - boolean
	 * if true - shows empty view, otherwise - hides
	 */
	void showEmptyView(boolean isShow);

	/**
	 * Method to show message to user in view
	 */
	void showMessage();
}
