package com.bankex.pay.domain.interactor;

import com.bankex.pay.data.entity.ContactModel;
import java.util.List;

/**
 * Class that implements @IContactsInteractor interface
 */
public class ContactInteractor implements IContactsInteractor {

	@Override public List<ContactModel> getSavedContacts() {
		return null;
	}

	@Override public void addContact(ContactModel contact) {

	}

	@Override public void deleteContact(String address) {

	}
}
