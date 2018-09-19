package com.bankex.pay.data.reporitories;

import com.bankex.pay.data.entity.mappers.ContactsMapper;
import com.bankex.pay.domain.models.ContactsModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * @author Denis Anisimov.
 */
@Singleton
public class ContactRepository {

    private final ContactsDataSourceRemote mContactsDataSourceRemote;

    @Inject
    public ContactRepository(ContactsDataSourceRemote contactsDataSourceRemote) {
        this.mContactsDataSourceRemote = contactsDataSourceRemote;
    }

    public Observable<ContactsModel> getContacts() {
        return mContactsDataSourceRemote.getContacts().map(contactsEntity -> ContactsMapper.transform(contactsEntity));
    }
}
