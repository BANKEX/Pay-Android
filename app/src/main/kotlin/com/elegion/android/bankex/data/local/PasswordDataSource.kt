package com.elegion.android.bankex.data.local

import android.content.Context
import android.os.Build
import com.elegion.android.bankex.data.model.ServiceErrorException
import com.elegion.android.bankex.data.model.Wallet
import com.elegion.android.bankex.util.KS
import com.wallet.pwd.trustapp.PasswordManager
import java.security.SecureRandom

/**
 * @author Denis Anisimov.
 */
class PasswordDataSource(val context: Context) {

    suspend fun generatePassword(): String {
        val bytes = ByteArray(256)
        val random = SecureRandom()
        random.nextBytes(bytes)
        return String(bytes)
    }

    suspend fun setPassword(wallet: Wallet, password: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            KS.put(context, wallet.address, password)
        } else {
            try {
                PasswordManager.setPassword(wallet.address, password, context)
            } catch (e: Exception) {
                throw ServiceErrorException(ServiceErrorException.KEY_STORE_ERROR)
            }

        }
    }

    suspend fun getPassword(wallet: Wallet): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val ks = KS[context, wallet.address];
            return String(ks!!)
        } else {
            try {
                return PasswordManager.getPassword(wallet.address, context)
            } catch (e: Exception) {
                throw ServiceErrorException(ServiceErrorException.KEY_STORE_ERROR)
            }
        }
    }
}
