package com.bankex.pay.presentation.ui.contactinfo;

import com.bankex.pay.presentation.ui.base.BaseView;

/**
 * Interface for contact info screen.
 */
public interface IContactInfoView extends BaseView {
	void popBackStack();

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
}
