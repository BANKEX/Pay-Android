package com.bankex.pay.data.model

import android.os.Parcel
import android.os.Parcelable

class TransactionOperation : Parcelable {
    var transactionId: String
    var viewType: String
    var from: String
    var to: String
    var value: String
    var contract: TransactionContract

    protected constructor(`in`: Parcel) {
        transactionId = `in`.readString()
        viewType = `in`.readString()
        from = `in`.readString()
        to = `in`.readString()
        value = `in`.readString()
        contract = `in`.readParcelable(TransactionContract::class.java.classLoader)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(transactionId)
        parcel.writeString(viewType)
        parcel.writeString(from)
        parcel.writeString(to)
        parcel.writeString(value)
        parcel.writeParcelable(contract, flags)
    }

    companion object {

        val CREATOR: Parcelable.Creator<TransactionOperation> = object : Parcelable.Creator<TransactionOperation> {
            override fun createFromParcel(`in`: Parcel): TransactionOperation {
                return TransactionOperation(`in`)
            }

            override fun newArray(size: Int): Array<TransactionOperation> {
                return emptyArray()
            }
        }
    }
}
