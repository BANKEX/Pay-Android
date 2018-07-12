package com.bankex.wallet.data.model

import android.os.Parcel
import android.os.Parcelable

class TransactionContract protected constructor(`in`: Parcel) : Parcelable {
    var address: String
    var name: String
    var totalSupply: String
    var decimals: Long = 0
    var symbol: String

    init {
        address = `in`.readString()
        name = `in`.readString()
        totalSupply = `in`.readString()
        decimals = `in`.readLong()
        symbol = `in`.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(address)
        parcel.writeString(name)
        parcel.writeString(totalSupply)
        parcel.writeLong(decimals)
        parcel.writeString(symbol)
    }

    companion object {

        val CREATOR: Parcelable.Creator<TransactionContract> = object : Parcelable.Creator<TransactionContract> {
            override fun createFromParcel(`in`: Parcel): TransactionContract {
                return TransactionContract(`in`)
            }

            override fun newArray(size: Int): Array<TransactionContract> {
                return emptyArray()
            }
        }
    }
}
