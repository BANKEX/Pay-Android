package com.bankex.pay.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Denis Anisimov.
 */
public class ContactEntity {

    @SerializedName("address")
    private String address;
    @SerializedName("firstname")
    private String firstName;
    @SerializedName("surname")
    private String surname;
    @SerializedName("avatarid")
    private String avatarId = null;

    public ContactEntity() {

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
}
