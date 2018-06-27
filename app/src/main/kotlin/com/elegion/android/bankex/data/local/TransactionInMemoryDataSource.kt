package com.elegion.android.bankex.data.local

import android.text.format.DateUtils
import com.elegion.android.bankex.data.model.Transaction
import com.elegion.android.bankex.data.model.Wallet

class TransactionInMemoryDataSource {
    private val cache = java.util.concurrent.ConcurrentHashMap<String, CacheUnit>()
    fun fetchTransaction(wallet: Wallet): Array<Transaction> {
        val unit = cache[wallet.address]
        var transactions = emptyArray<Transaction>()
        if (unit != null) {
            if (System.currentTimeMillis() - unit.create > MAX_TIME_OUT) {
                cache.remove(wallet.address)
            } else {
                transactions = unit.transactions
            }
        }
        return transactions
    }

    fun putTransactions(wallet: Wallet, transactions: Array<Transaction>) {
        cache[wallet.address] = CacheUnit(wallet.address, System.currentTimeMillis(), transactions)
    }

    fun clear() {
        cache.clear()
    }

    private class CacheUnit(internal val accountAddress: String, internal val create: Long, internal val transactions: Array<Transaction>)

    companion object {
        private val MAX_TIME_OUT = DateUtils.MINUTE_IN_MILLIS
    }
}
