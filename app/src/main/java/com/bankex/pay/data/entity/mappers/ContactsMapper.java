package com.bankex.pay.data.entity.mappers;

import com.bankex.pay.data.entity.ContactEntity;
import com.bankex.pay.data.entity.ContactsEntity;
import com.bankex.pay.domain.models.ContactModel;
import com.bankex.pay.domain.models.ContactsModel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Маппер из серверного энтити с модель приложения на случай переезда
 *
 * @author Denis Anisimov.
 */
public class ContactsMapper {
    public static ContactsModel transform(ContactsEntity ContactsEntity) {
        ContactsModel ContactsModel = null;
        if (ContactsEntity != null) {
            ContactsModel = new ContactsModel();
            if (ContactsEntity.getContacts() != null) {
                ContactsModel.setContacts(transform(ContactsEntity.getContacts()));
            }
        }

        return ContactsModel;
    }

    private static HashMap<String, ContactModel> transform(HashMap<String, ContactEntity> Contacts) {
        HashMap<String, ContactModel> result = new HashMap<>();
        Iterator it = Contacts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ContactEntity> pair = (Map.Entry) it.next();
            result.put(pair.getKey(), ContactMapper.transform(pair.getValue()));
        }

        return result;
    }
}
