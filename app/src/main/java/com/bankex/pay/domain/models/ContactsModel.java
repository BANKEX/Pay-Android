package com.bankex.pay.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Denis Anisimov.
 */
public class ContactsModel {

    private HashMap<String, ContactModel> mContacts;

    public ContactsModel() {
    }

    public void setContacts(HashMap<String, ContactModel> contacts) {
        mContacts = contacts;
    }

    public boolean isEmpty() {
        return (this.mContacts == null || this.mContacts.isEmpty());
    }

    public ArrayList<ContactModel> toDisplayedList() {

        ArrayList<ContactModel> orderedList = new ArrayList<ContactModel>(this.mContacts.values());
        Collections.sort(orderedList);
        return orderedList;
    }
}
