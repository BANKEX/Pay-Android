package com.bankex.pay.domain.interactor;

import com.bankex.pay.data.repository.IContactsRepository;
import com.bankex.pay.domain.model.ContactModel;
import java.util.List;

/**
 * Implements {@link IContactsInteractor} interface.
 */
public class ContactInteractor implements IContactsInteractor {
	private IContactsRepository contactsRepository;

	public ContactInteractor(IContactsRepository contactsRepository) {
		this.contactsRepository = contactsRepository;
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public List<ContactModel> getSavedContacts() {
		return contactsRepository.getAllContacts();
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public ContactModel getSavedContacts(String contactId) {
		return contactsRepository.getContactById(contactId);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void addContact(ContactModel contact) {
		contactsRepository.addContact(contact);
	}

	/**
	 * {@inheritDoc }
	 */
	@Override public void deleteContact(String address) {
		contactsRepository.deleteContactById(address);
	}
}
