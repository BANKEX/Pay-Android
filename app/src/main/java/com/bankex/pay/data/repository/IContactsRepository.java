package com.bankex.pay.data.repository;

import com.bankex.pay.data.entity.ContactModel;
import io.reactivex.Single;
import io.realm.RealmResults;
import javax.annotation.Nullable;

/**
 * Repository to work with contacts saved in database.
 */
public interface IContactsRepository {

	/**
	 * Method to get certain contact by id.
	 *
	 * @param id - contact`s address.
	 * @return ContactModel - full contact info or null if there is no such item.
	 */
	@Nullable
	ContactModel getContactById(String id);

	/**
	 * Method to return all contacts saved in database.
	 *
	 * @return contact list
	 */
	RealmResults<ContactModel> getAllContacts();

	/**
	 * Method to insert contact item in database.
	 *
	 * @param contact - item to inset in database.
	 * @return added item.
	 */
	Single<ContactModel> addContact(ContactModel contact);

	/**
	 * Method to delete contact item by its` id.
	 *
	 * @param id - contact`s address.
	 */
	void deleteContactById(String id);
}