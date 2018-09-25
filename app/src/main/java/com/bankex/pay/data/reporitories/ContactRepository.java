package com.bankex.pay.data.reporitories;

import com.bankex.pay.data.entity.mappers.ContactsMapper;
import com.bankex.pay.domain.models.ContactModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.MaybeOnSubscribe;

/**
 * Общий репозиторий для контактов
 *
 * @author Denis Anisimov.
 */
@Singleton
public class ContactRepository {

    private final ContactsDataSourceRemote mContactsDataSourceRemote;

    @Inject
    public ContactRepository(ContactsDataSourceRemote contactsDataSourceRemote) {
        this.mContactsDataSourceRemote = contactsDataSourceRemote;
    }

    public Maybe<List<ContactModel>> getContacts() {
        return Maybe.defer((Callable) (() -> Maybe
                .create((MaybeOnSubscribe)
                        (it -> it.onSuccess(mContactsDataSourceRemote.getContacts()
                                .map(contactsEntity -> ContactsMapper
                                        .transform(contactsEntity)))))));
    }
}
