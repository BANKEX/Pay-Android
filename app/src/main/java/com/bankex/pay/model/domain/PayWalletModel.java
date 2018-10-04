package com.bankex.pay.model.domain;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.annotations.PrimaryKey;

public class PayWalletModel implements Parcelable {

    /**
     * Деволтное имя кошелька
     */
    public static final String ETH_WALLET_NAME = "ETH Walle Name";

    /**
     * Адресс кошелька
     */
    @PrimaryKey
    public final String address;

    /**
     * Название кошелька
     */
    public String name;

    /**
     * Хранилище
     */
    public String keyStore;

    /**
     * Ключ
     */
    private byte[] key;


    /**
     * @param address - адресс кошелька
     */
    public PayWalletModel(String address) {
        this.address = address;
        this.name = ETH_WALLET_NAME;
        key = new byte[1];
    }

    /**
     * @param address - адресс кошелька
     * @param name    - название кошелька
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
