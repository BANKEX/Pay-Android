package com.bankex.pay.domain.models;

import android.support.annotation.NonNull;

/**
 * @author Denis Anisimov.
 */
public class ContactModel implements Comparable<ContactModel> {

    private String address;
    private String firstName;
    private String surname;
    private String avatarId = null;

    public ContactModel(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    @Override
    public int compareTo(@NonNull ContactModel contactModel) {
        return this.firstName.compareTo(contactModel.firstName);
    }
}
