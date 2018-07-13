package com.bankex.pay.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Denis Anisimov.
 */
class Wallet : Parcelable {
    val address: String

    constructor(address: String) {
        this.address = address
    }

    private constructor(`in`: Parcel) {
        address = `in`.readString()
    }

    fun sameAddress(address: String?): Boolean {
        return this.address == address
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(address)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Wallet> = object : Parcelable.Creator<Wallet> {
            override fun createFromParcel(`in`: Parcel): Wallet {
                return Wallet(`in`)
            }

            override fun newArray(size: Int): Array<out Wallet>? {
                return emptyArray()
            }
        }
    }
}