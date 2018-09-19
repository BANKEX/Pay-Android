package com.bankex.pay.data.entity;

import com.google.firebase.database.Exclude;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

import io.realm.internal.Keep;

/**
 * @author Denis Anisimov.
 */
@Keep
public class ContactsEntity {

    @SerializedName("contacts")
    private HashMap<String, ContactEntity> mContacts = null;

    public ContactsEntity() {

    }

    public HashMap<String, ContactEntity> getContacts() {
        return mContacts;
    }

    @Exclude
    public boolean isEmpty() {
        return (this.mContacts == null || this.mContacts.isEmpty());
    }

    public void setContacts(HashMap<String, ContactEntity> contacts) {
        this.mContacts = contacts;
    }
}
