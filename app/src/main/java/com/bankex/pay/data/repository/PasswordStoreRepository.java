package com.bankex.pay.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.bankex.pay.model.domain.PayWalletModel;
import com.bankex.pay.model.domain.ServiceErrorException;
import com.bankex.pay.utils.KS;
import com.wallet.pwd.trustapp.PasswordManager;

import java.security.SecureRandom;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * @author Gevork Safaryan on 04/10/2018
 */
public class PasswordStoreRepository implements IPasswordStoreRepository {

    private final Context context;

    public PasswordStoreRepository(Context context) {
        this.context = context;

        migrate();
    }

    private void migrate() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        Map<String, ?> passwords = pref.getAll();
        for (String key : passwords.keySet()) {
            if (key.contains("-pwd")) {
                String address = key.replace("-pwd", "");
                try {
                    KS.put(context, address.toLowerCase(), PasswordManager.getPassword(address, context));
                } catch (Exception ex) {
                    Toast.makeText(context, "Could not process passwords.", Toast.LENGTH_LONG)
                            .show();
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public Single<String> loadPassword(PayWalletModel wallet) {
        return Single.fromCallable(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return new String(KS.get(context, wallet.address));
            } else {
                try {
                    return PasswordManager.getPassword(wallet.address, context);
                } catch (Exception e) {
                    throw new ServiceErrorException(ServiceErrorException.KEY_STORE_ERROR);
                }
            }
        });
    }

    @Override
    public Completable savePassword(PayWalletModel wallet, String password) {
        return Completable.fromAction(() -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                KS.put(context, wallet.address, password);
            } else {
                try {
                    PasswordManager.setPassword(wallet.address, password, context);
                } catch (Exception e) {
                    throw new ServiceErrorException(ServiceErrorException.KEY_STORE_ERROR);
                }
            }
        });
    }

    @Override
    public Single<String> generatePassword() {
        return Single.fromCallable(() -> {
            byte bytes[] = new byte[256];
            SecureRandom random = new SecureRandom();
            random.nextBytes(bytes);
            return new String(bytes);
        });
    }
}
