package com.bankex.pay.data.repository;

import com.bankex.pay.data.realm.IRealmService;
import com.bankex.pay.domain.model.ContactModel;
import java.util.List;
import javax.annotation.Nullable;

/**
 * {@link IContactsRepository} repository implementation.
 */
public class ContactsRepository implements IContactsRepository {
	private final IRealmService mRealmService;

	public ContactsRepository(IRealmService mRealmService) {
		this.mRealmService = mRealmService;
	}

	/**
	 * {@inheritDoc }
	 */
	@Nullable
	@Override public ContactModel getContactById(String id) {
		return mRealmService.getContactById(id);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public List<ContactModel> getAllContacts() {
		return mRealmService.getAllContacts();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void addContact(ContactModel contact) {
		mRealmService.addContact(contact);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void deleteContactById(String id) {
		this.mRealmService.deleteContactById(id);
	}
}
