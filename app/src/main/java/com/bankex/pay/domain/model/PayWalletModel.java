package com.bankex.pay.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Realm table to store user wallet data.
 */
@RealmClass
public class PayWalletModel extends RealmObject implements Parcelable {

	/**
	 * Default wallet name.
	 */
	public static final String ETH_WALLET_NAME = "ETH Wallet Name";

	/**
	 * Wallet address, also primary key in database.
	 */
	@PrimaryKey
	public String address;

	/**
	 * Wallet name.
	 */
	public String name;

	/**
	 * Key store.
	 */
	public String keyStore;

	/**
	 * Key.
	 */
	private byte[] key;

	public PayWalletModel() {
		this.address = null;
	}

	/**
	 * Constructor to create model by wallet address.
	 *
	 * @param address wallet address
	 */
	public PayWalletModel(String address) {
		this.address = address;
		this.name = ETH_WALLET_NAME;
		key = new byte[1];
	}

	/**
	 * Constructor to create model by wallet name and address.
	 *
	 * @param address wallet address
	 * @param name wallet name
	 */
	public PayWalletModel(String address, String name) {
		this.address = address;
		this.name = name;

	}

	private PayWalletModel(Parcel in) {
		address = in.readString();
		name = in.readString();
		keyStore = in.readString();
		key = in.createByteArray();
		key.toString();
	}

	public static final Creator<PayWalletModel> CREATOR = new Creator<PayWalletModel>() {
		@Override
		public PayWalletModel createFromParcel(Parcel in) {
			return new PayWalletModel(in);
		}

		@Override
		public PayWalletModel[] newArray(int size) {
			return new PayWalletModel[size];
		}
	};

	public boolean sameAddress(String address) {
		return this.address.equals(address);
	}

	public PayWalletModel setName(String name) {
		this.name = name;
		return this;
	}

	public PayWalletModel setKeyStore(String keyStore) {
		this.keyStore = keyStore;
		return this;
	}

	public PayWalletModel setKey(byte[] key) {
		this.key = key;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public byte[] getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(address);
		parcel.writeString(name);
		parcel.writeString(keyStore);
		parcel.writeByteArray(key);
	}
}
