package com.bankex.pay.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Class to save into and get from Realm database information about contacts.
 */
@RealmClass
public class ContactModel extends RealmObject implements Parcelable {
	/**
	 * Address of the stored contact.
	 * In database is used as primary key, because it`s unique data.
	 */
	@PrimaryKey
	private String address;

	/**
	 * Contacts` name.
	 */
	private String name;

	/**
	 * Contacts` surname.
	 */
	private String surname;

	/**
	 * Extra data.
	 * Unnecessary when saving new contact to database.
	 * Needs when showing contact list and group contacts in alphabetical groups.
	 */
	private String groupLetterMarker;

	public ContactModel() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGroupLetterMarker() {
		return groupLetterMarker;
	}

	public void setGroupLetterMarker(String groupLetterMarker) {
		this.groupLetterMarker = groupLetterMarker;
	}

	protected ContactModel(Parcel in) {
		in.writeString(name);
		in.writeString(address);
	}

	public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
		@Override
		public ContactModel createFromParcel(Parcel in) {
			return new ContactModel(in);
		}

		@Override
		public ContactModel[] newArray(int size) {
			return new ContactModel[size];
		}
	};

	@Override public int describeContents() {
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(surname);
		dest.writeString(address);
		dest.writeString(String.valueOf(groupLetterMarker));
	}
}
