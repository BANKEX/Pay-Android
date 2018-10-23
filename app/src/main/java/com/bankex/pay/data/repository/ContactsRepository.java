package com.bankex.pay.data.repository;

import com.bankex.pay.data.entity.Contact;
import com.bankex.pay.data.realm.IRealmService;
import io.reactivex.Single;

/**
 * // TODO write comments
 */
public class ContactsRepository implements IContactsRepository {
	private final IRealmService mRealmService;

	public ContactsRepository(IRealmService mRealmService) {
		this.mRealmService = mRealmService;
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public Contact getContactById(String id) {
		return null;
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public Single<Contact> getAllContacts() {
		return null;
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void addContact(Contact contact) {

	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void deleteContactById(String id) {

	}
}
