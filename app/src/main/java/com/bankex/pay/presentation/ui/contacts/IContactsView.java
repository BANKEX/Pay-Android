package com.bankex.pay.presentation.ui.contacts;

import com.bankex.pay.data.entity.ContactModel;
import com.bankex.pay.presentation.ui.base.BaseView;
import java.util.List;

/**
 * View interface for contacts screen.
 */
public interface IContactsView extends BaseView {

	/**
	 * Method that shows contacts list recycler.
	 *
	 * @param isShow - boolean.
	 * If true - shows contacts, otherwise - hides.
	 */
	void showContactsList(boolean isShow);

	/**
	 * Method that shows empty view.
	 *
	 * @param isShow - boolean.
	 * If true - shows empty view, otherwise - hides.
	 */
	void showEmptyView(boolean isShow);

	/**
	 * Method to get all saved contacts from database and set them into adapter.
	 */
	void setContacts(List<ContactModel> contacts);
}
