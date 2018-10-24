package com.bankex.pay.data.entity;

import android.os.Parcel;
import android.os.Parcelable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContactModel extends RealmObject implements Parcelable {

	private String name;

	private String surname;

	@PrimaryKey
	private String address;

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
		dest.writeString(address);
	}
}
