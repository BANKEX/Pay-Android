package com.bankex.pay.data.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName

import java.util.Arrays

class Transaction : Parcelable {
    @SerializedName("id")
    val hash: String
    val blockNumber: String
    val timeStamp: Long
    val nonce: Int
    val from: String
    val to: String
    val value: String
    val gas: String
    val gasPrice: String
    val gasUsed: String
    val input: String
    val operations: Array<TransactionOperation>?
    val error: String

    constructor(
            hash: String,
            error: String,
            blockNumber: String,
            timeStamp: Long,
            nonce: Int,
            from: String,
            to: String,
            value: String,
            gas: String,
            gasPrice: String,
            input: String,
            gasUsed: String,
            operations: Array<TransactionOperation>) {
        this.hash = hash
        this.error = error
        this.blockNumber = blockNumber
        this.timeStamp = timeStamp
        this.nonce = nonce
        this.from = from
        this.to = to
        this.value = value
        this.gas = gas
        this.gasPrice = gasPrice
        this.input = input
        this.gasUsed = gasUsed
        this.operations = operations
    }

    protected constructor(`in`: Parcel) {
        hash = `in`.readString()
        error = `in`.readString()
        blockNumber = `in`.readString()
        timeStamp = `in`.readLong()
        nonce = `in`.readInt()
        from = `in`.readString()
        to = `in`.readString()
        value = `in`.readString()
        gas = `in`.readString()
        gasPrice = `in`.readString()
        input = `in`.readString()
        gasUsed = `in`.readString()
        val parcelableArray = `in`.readParcelableArray(TransactionOperation::class.java.classLoader)
        var operations: Array<TransactionOperation>? = null
        if (parcelableArray != null) {
            operations = Arrays.copyOf(parcelableArray, parcelableArray.size, Array<TransactionOperation>::class.java)
        }
        this.operations = operations
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(hash)
        dest.writeString(error)
        dest.writeString(blockNumber)
        dest.writeLong(timeStamp)
        dest.writeInt(nonce)
        dest.writeString(from)
        dest.writeString(to)
        dest.writeString(value)
        dest.writeString(gas)
        dest.writeString(gasPrice)
        dest.writeString(input)
        dest.writeString(gasUsed)
        dest.writeParcelableArray(operations, flags)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Transaction> = object : Parcelable.Creator<Transaction> {
            override fun createFromParcel(`in`: Parcel): Transaction {
                return Transaction(`in`)
            }

            override fun newArray(size: Int): Array<Transaction> {
                return emptyArray()
            }
        }
    }
}
