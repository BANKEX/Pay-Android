package com.bankex.pay.data.repository;

import com.bankex.pay.data.entity.ContactModel;
import com.bankex.pay.data.realm.IRealmService;
import io.reactivex.Single;
import io.realm.RealmResults;

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
	@Override public ContactModel getContactById(String id) {
		return mRealmService.getContactById(id);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public RealmResults<ContactModel> getAllContacts() {
		return mRealmService.getAllContacts();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public Single<ContactModel> addContact(ContactModel contact) {
		return mRealmService.addContact(contact);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void deleteContactById(String id) {
		this.mRealmService.deleteContactById(id);
	}
}
