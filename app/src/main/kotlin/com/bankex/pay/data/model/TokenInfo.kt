package com.bankex.pay.data.model

import android.os.Parcel
import android.os.Parcelable

class TokenInfo : Parcelable {
    var address: String
    var name: String
    var symbol: String
    var decimals: Int

    constructor(address: String, name: String, symbol: String, decimals: Int) {
        this.address = address
        this.name = name
        this.symbol = symbol
        this.decimals = decimals
    }

    constructor(`in`: Parcel) {
        address = `in`.readString()
        name = `in`.readString()
        symbol = `in`.readString()
        decimals = `in`.readInt()
    }

    val CREATOR: Parcelable.Creator<TokenInfo> = object : Parcelable.Creator<TokenInfo> {
        override fun createFromParcel(`in`: Parcel): TokenInfo {
            return TokenInfo(`in`)
        }

        override fun newArray(size: Int): Array<TokenInfo> {
            return emptyArray()
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(address)
        dest.writeString(name)
        dest.writeString(symbol)
        dest.writeInt(decimals)
    }
}
