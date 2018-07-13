package com.bankex.pay.data.model


import android.os.Parcel
import android.os.Parcelable

import java.math.BigInteger

class GasSettings : Parcelable {
    val gasPrice: BigInteger
    val gasLimit: BigInteger

    constructor(gasPrice: BigInteger, gasLimit: BigInteger) {
        this.gasPrice = gasPrice
        this.gasLimit = gasLimit
    }

    private constructor(`in`: Parcel) {
        gasPrice = BigInteger(`in`.readString())
        gasLimit = BigInteger(`in`.readString())
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(gasPrice.toString(10))
        parcel.writeString(gasLimit.toString(10))
    }

    companion object {

        val CREATOR: Parcelable.Creator<GasSettings> = object : Parcelable.Creator<GasSettings> {
            override fun createFromParcel(`in`: Parcel): GasSettings {
                return GasSettings(`in`)
            }

            override fun newArray(size: Int): Array<GasSettings> {
                return emptyArray()
            }
        }
    }
}
