package com.elegion.android.bankex.data.database

import android.arch.persistence.room.*
import android.location.Address
import org.web3j.crypto.Wallet

/**
 * @author Denis Anisimov.
 */
@Database(entities = [ (Address::class), (Wallet::class) ], version = 1)
abstract class BehanceDatabase : RoomDatabase() {

    abstract val behanceDao: BankexDao
}

@Dao
public interface BankexDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertWallet(wallet: Wallet)
}