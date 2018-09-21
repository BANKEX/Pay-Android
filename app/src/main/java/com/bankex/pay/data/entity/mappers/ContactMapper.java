package com.bankex.pay.data.entity.mappers;

import com.bankex.pay.data.entity.ContactEntity;
import com.bankex.pay.domain.models.ContactModel;

/**
 * Маппер из серверного энтити с модель приложения  на случай переезда
 *
 * @author Denis Anisimov.
 */
class ContactMapper {

    public static ContactModel transform(ContactEntity contactEntity) {
        ContactModel contactModel = null;
        if (contactEntity != null) {
            contactModel = new ContactModel(contactEntity.getAddress());
            contactModel.setFirstName(contactEntity.getFirstName());
            contactModel.setSurname(contactEntity.getSurname());
            contactModel.setAvatarId(contactEntity.getAvatarId());
        }
        return contactModel;
    }
}
