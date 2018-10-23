package com.bankex.pay.data.repository;

import com.bankex.pay.data.entity.Contact;
import io.reactivex.Single;

/**
 * // TODO write comments
 */
public interface IContactsRepository {

	/**
	 *
	 * @param id - contact`s address
	 * @return Contact - full contact info
	 */
	Contact getContactById(String id);

	Single<Contact> getAllContacts();

	void addContact(Contact contact);

	void deleteContactById(String id);
}
