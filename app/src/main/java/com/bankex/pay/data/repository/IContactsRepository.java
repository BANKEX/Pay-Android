package com.bankex.pay.data.repository;

import com.bankex.pay.data.entity.ContactModel;
import io.reactivex.Single;
import io.realm.RealmResults;

/**
 * TODO write comments
 */
public interface IContactsRepository {

	/**
	 * @param id - contact`s address
	 * @return ContactModel - full contact info
	 */
	ContactModel getContactById(String id);

	RealmResults<ContactModel> getAllContacts();

	Single<ContactModel> addContact(ContactModel contact);

	void deleteContactById(String id);
}