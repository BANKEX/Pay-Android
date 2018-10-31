package com.bankex.pay.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TODO write comments
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ContactModel extends RealmObject implements Parcelable {

	@PrimaryKey
	private String address;

	private String name;

	private String surname;

	private String groupLetterMarker;

	public ContactModel() {
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
