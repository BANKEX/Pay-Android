package com.elegion.android.bankex.util

import android.annotation.TargetApi
import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.UserNotAuthenticatedException
import android.util.Log
import com.elegion.android.bankex.data.model.ServiceErrorException
import com.elegion.android.bankex.data.model.ServiceErrorException.Companion.INVALID_KEY
import com.elegion.android.bankex.data.model.ServiceErrorException.Companion.IV_OR_ALIAS_NO_ON_DISK
import com.elegion.android.bankex.data.model.ServiceErrorException.Companion.KEY_IS_GONE
import com.elegion.android.bankex.data.model.ServiceErrorException.Companion.KEY_STORE_ERROR
import com.elegion.android.bankex.data.model.ServiceErrorException.Companion.USER_NOT_AUTHENTICATED
import java.io.*
import java.security.*
import java.security.cert.CertificateException
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec

/**
 * @author Denis Anisimov.
 */
@TargetApi(23)
object KS {
    private val TAG = "KS"

    private val ANDROID_KEY_STORE = "AndroidKeyStore"
    private val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
    private val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
    private val CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding"

    @Synchronized
    @Throws(ServiceErrorException::class)
    private fun setData(
            context: Context,
            data: ByteArray?,
            alias: String,
            aliasFile: String,
            aliasIV: String): Boolean {
        if (data == null) {
            throw ServiceErrorException(
                    ServiceErrorException.INVALID_DATA, "keystore insert data is null")
        }
        val keyStore: KeyStore
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
            keyStore.load(null)
            // Create the keys if necessary
            if (!keyStore.containsAlias(alias)) {
                val keyGenerator = KeyGenerator.getInstance(
                        KeyProperties.KEY_ALGORITHM_AES,
                        ANDROID_KEY_STORE)

                // Set the alias of the entry in Android KeyStore where the key will appear
                // and the constrains (purposes) in the constructor of the Builder
                keyGenerator.init(KeyGenParameterSpec.Builder(
                        alias,
                        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                        .setBlockModes(BLOCK_MODE)
                        .setKeySize(256)
                        .setUserAuthenticationRequired(false)
                        .setRandomizedEncryptionRequired(true)
                        .setEncryptionPaddings(PADDING)
                        .build())
                keyGenerator.generateKey()
            }
            val encryptedDataFilePath = getFilePath(context, aliasFile)
            val secret = keyStore.getKey(alias, null) as SecretKey ?: throw ServiceErrorException(
                    ServiceErrorException.KEY_STORE_SECRET,
                    "secret is null on setData: $alias")
            val inCipher = Cipher.getInstance(CIPHER_ALGORITHM)
            inCipher.init(Cipher.ENCRYPT_MODE, secret)
            val iv = inCipher.iv
            val path = getFilePath(context, aliasIV)
            val success = writeBytesToFile(path, iv)
            if (!success) {
                keyStore.deleteEntry(alias)
                throw ServiceErrorException(
                        ServiceErrorException.FAIL_TO_SAVE_IV_FILE,
                        "Failed to save the iv file for: $alias")
            }
            var cipherOutputStream: CipherOutputStream? = null
            try {
                cipherOutputStream = CipherOutputStream(
                        FileOutputStream(encryptedDataFilePath),
                        inCipher)
                cipherOutputStream.write(data)
            } catch (ex: Exception) {
                throw ServiceErrorException(
                        ServiceErrorException.KEY_STORE_ERROR,
                        "Failed to save the file for: $alias")
            } finally {
                if (cipherOutputStream != null) {
                    cipherOutputStream.close()
                }
            }
            return true
        } catch (e: UserNotAuthenticatedException) {
            throw ServiceErrorException(USER_NOT_AUTHENTICATED)
        } catch (ex: ServiceErrorException) {
            Log.d(TAG, "Key store error", ex)
            throw ex
        } catch (ex: Exception) {
            when (ex) {
                is IOException, is CertificateException, is KeyStoreException,
                is UnrecoverableKeyException, is NoSuchAlgorithmException,
                is NoSuchPaddingException, is InvalidAlgorithmParameterException -> {
                    Log.d(TAG, "Key store error", ex)
                    throw ServiceErrorException(KEY_STORE_ERROR)
                }
                else -> throw ex
            }
        }

    }

    @Synchronized
    @Throws(ServiceErrorException::class)
    private fun getData(
            context: Context,
            alias: String,
            aliasFile: String,
            aliasIV: String): ByteArray? {
        val keyStore: KeyStore
        val encryptedDataFilePath = getFilePath(context, aliasFile)
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
            keyStore.load(null)
            val secretKey = keyStore.getKey(alias, null) as SecretKey
            if (secretKey == null) {
                /* no such key, the key is just simply not there */
                val fileExists = File(encryptedDataFilePath).exists()
                if (!fileExists) {
                    return null/* file also not there, fine then */
                }
                throw ServiceErrorException(
                        KEY_IS_GONE,
                        "file is present but the key is gone: $alias")
            }

            val ivExists = File(getFilePath(context, aliasIV)).exists()
            val aliasExists = File(getFilePath(context, aliasFile)).exists()
            if (!ivExists || !aliasExists) {
                removeAliasAndFiles(context, alias, aliasFile, aliasIV)
                //report it if one exists and not the other.
                if (ivExists != aliasExists) {
                    throw ServiceErrorException(
                            IV_OR_ALIAS_NO_ON_DISK,
                            "file is present but the key is gone: $alias")
                } else {
                    throw ServiceErrorException(
                            IV_OR_ALIAS_NO_ON_DISK,
                            "!ivExists && !aliasExists: $alias")
                }
            }

            val iv = readBytesFromFile(getFilePath(context, aliasIV))
            if (iv == null || iv.size == 0) {
                throw NullPointerException("iv is missing for $alias")
            }
            val outCipher = Cipher.getInstance(CIPHER_ALGORITHM)
            outCipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
            val cipherInputStream = CipherInputStream(FileInputStream(encryptedDataFilePath), outCipher)
            return readBytesFromStream(cipherInputStream)
        } catch (e: InvalidKeyException) {
            if (e is UserNotAuthenticatedException) {
                //				showAuthenticationScreen(context, requestCode);
                throw ServiceErrorException(USER_NOT_AUTHENTICATED)
            } else {
                throw ServiceErrorException(INVALID_KEY)
            }
        } catch (e: Exception) {

            throw ServiceErrorException(KEY_STORE_ERROR)
        }

    }

    @Synchronized
    private fun getFilePath(context: Context, fileName: String): String {
        return File(context.filesDir, fileName).absolutePath
    }

    private fun writeBytesToFile(path: String, data: ByteArray): Boolean {
        var fos: FileOutputStream? = null
        try {
            val file = File(path)
            fos = FileOutputStream(file)
            // Writes bytes from the specified byte array to this file output stream
            fos.write(data)
            return true
        } catch (e: FileNotFoundException) {
            println("File not found$e")
        } catch (ioe: IOException) {
            println("Exception while writing file $ioe")
        } finally {
            // close the streams using close method
            try {
                if (fos != null) {
                    fos.close()
                }
            } catch (ioe: IOException) {
                println("Error while closing stream: $ioe")
            }

        }
        return false
    }

    @Synchronized
    private fun removeAliasAndFiles(context: Context, alias: String, dataFileName: String, ivFileName: String) {
        val keyStore: KeyStore
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)
            keyStore.load(null)
            keyStore.deleteEntry(alias)
            File(getFilePath(context, dataFileName)).delete()
            File(getFilePath(context, ivFileName)).delete()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun readBytesFromStream(`in`: InputStream): ByteArray {
        // this dynamically extends to take the bytes you read
        val byteBuffer = ByteArrayOutputStream()
        // this is storage overwritten on each iteration with bytes
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        // we need to know how may bytes were read to write them to the byteBuffer
        var len: Int
        try {
            len = `in`.read(buffer)
            while (len != -1) {
                byteBuffer.write(buffer, 0, len)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                byteBuffer.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (`in` != null)
                try {
                    `in`.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

        }
        // and then we can return your byte array.
        return byteBuffer.toByteArray()
    }

    private fun readBytesFromFile(path: String): ByteArray? {
        var bytes: ByteArray? = null
        val fin: FileInputStream
        try {
            val file = File(path)
            fin = FileInputStream(file)
            bytes = readBytesFromStream(fin)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return bytes
    }

    @Throws(ServiceErrorException::class)
    fun put(context: Context, address: String, password: String) {
        setData(context, password.toByteArray(), address, address, address + "iv")
    }

    @Throws(ServiceErrorException::class)
    operator fun get(context: Context, address: String): ByteArray? {
        return getData(context, address, address, address + "iv")
    }

    /* fun showAuthenticationScreen(context: Context, requestCode: Int) {
         // Create the Confirm Credentials screen. You can customize the title and description. Or
         // we will provide a generic one for you if you leave it null
         Log.e(TAG, "showAuthenticationScreen: ")
         if (context is Activity) {
             val mKeyguardManager = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
                     ?: return
             val intent = mKeyguardManager
                     .createConfirmDeviceCredentialIntent(
                             context.getString(R.string.unlock_screen_title_android),
                             context.getString(R.string.unlock_screen_prompt_android))
             if (intent != null) {
                 context.startActivityForResult(intent, requestCode)
             } else {
                 Log.e(TAG, "showAuthenticationScreen: failed to create intent for auth")
                 context.finish()
             }
         } else {
             Log.e(TAG, "showAuthenticationScreen: context is not activity!")
         }
     }*/
}