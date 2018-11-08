package com.bankex.pay.presentation.navigation.contacts;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

/**
 * Router for whole contact branch.
 */
public interface IContactsRouter {
	/**
	 * Opens fragment for creating new contact.
	 */
	void openAddContactFragment(FragmentActivity activity, Context context);

	/**
	 * Opens fragment to show contact information and transactions.
	 *
	 * @param contactId contact id, String
	 */
	void openContactFragment(FragmentActivity activity, Context context, String contactId);

	/**
	 * Opens dialog to delete existing contact.
	 *
	 * @param contactId contact id, String
	 */
	void openDeleteContactDialog(FragmentActivity activity, Context context, String contactId);
}
