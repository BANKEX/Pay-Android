package com.bankex.pay.data.entity;

import android.os.Parcel;
import android.os.Parcelable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Contact extends RealmObject implements Parcelable {
	@PrimaryKey
	private String name;
	private String address;

	public Contact() {
	}

	protected Contact(Parcel in) {
		in.writeString(name);
		in.writeString(address);
	}

	public static final Creator<Contact> CREATOR = new Creator<Contact>() {
		@Override
		public Contact createFromParcel(Parcel in) {
			return new Contact(in);
		}

		@Override
		public Contact[] newArray(int size) {
			return new Contact[size];
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
