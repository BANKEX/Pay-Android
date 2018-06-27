package com.elegion.android.bankex.data.model

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal

/**
 * @author Denis Anisimov.
 */
class Token : Parcelable {
    val tokenInfo: TokenInfo
    val balance: BigDecimal

    constructor(tokenInfo: TokenInfo, balance: BigDecimal) {
        this.tokenInfo = tokenInfo
        this.balance = balance
    }

    private constructor(`in`: Parcel) {
        tokenInfo = `in`.readParcelable<Parcelable>(TokenInfo::class.java!!.getClassLoader()) as TokenInfo
        balance = BigDecimal(`in`.readString())
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(tokenInfo, flags)
        dest.writeString(balance.toString())
    }

    companion object {

        val CREATOR: Parcelable.Creator<Token> = object : Parcelable.Creator<Token> {
            override fun createFromParcel(`in`: Parcel): Token {
                return Token(`in`)
            }

            override fun newArray(size: Int): Array<Token> {
                return emptyArray()
            }
        }
    }
}