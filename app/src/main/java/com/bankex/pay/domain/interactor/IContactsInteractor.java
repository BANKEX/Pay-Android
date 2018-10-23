package com.bankex.pay.domain.interactor;

import com.bankex.pay.data.entity.Contact;
import java.util.List;

public interface IContactsInteractor {

	/**
	 * Method that gets all user`s contacts from database
	 * @return List<Contact> - contact list
	 */
	List<Contact> getSavedContacts();

	/**
	 * Method that saves only one contact to database
	 * @param contact - new created contact
	 */
	void addContact(Contact contact);

	/**
	 * Method that deletes chosen contact
	 * @param address - id of contact to delete
	 */
	void deleteContact(String address);
}
