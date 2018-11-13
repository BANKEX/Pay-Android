package com.bankex.pay.domain.interactor;

import com.bankex.pay.domain.model.ContactModel;
import java.util.List;

/**
 * Interface for operations with user contacts.
 */
public interface IContactsInteractor {

	/**
	 * Method that gets all user`s contacts from database.
	 *
	 * @return List<ContactModel> - contact list.
	 */
	List<ContactModel> getSavedContacts();

	/**
	 * Method that gets certain contact from database
	 * by its` id.
	 *
	 * @return List<ContactModel> - contact list.
	 */
	ContactModel getSavedContacts(String contactId);

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
