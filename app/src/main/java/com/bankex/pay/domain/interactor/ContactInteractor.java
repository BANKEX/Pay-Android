package com.bankex.pay.domain.interactor;

import com.bankex.pay.data.entity.Contact;
import java.util.List;

/**
 * Class that implements @IContactsInteractor interface
 */
public class ContactInteractor implements IContactsInteractor {

	@Override public List<Contact> getSavedContacts() {
		return null;
	}

	@Override public void addContact(Contact contact) {

	}

	@Override public void deleteContact(String address) {

	}
}
