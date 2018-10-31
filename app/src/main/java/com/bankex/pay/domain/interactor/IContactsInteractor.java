package com.bankex.pay.domain.interactor;

import com.bankex.pay.domain.model.ContactModel;
import java.util.List;

public interface IContactsInteractor {

	/**
	 * Method that gets all user`s contacts from database.
	 *
	 * @return List<ContactModel> - contact list.
	 */
	List<ContactModel> getSavedContacts();

	/**
	 * Method that saves only one contact to database.
	 *
	 * @param contact - new created contact.
	 */
	void addContact(ContactModel contact);

	/**
	 * Method that deletes chosen contact.
	 *
	 * @param address - id of contact to delete.
	 */
	void deleteContact(String address);
}
