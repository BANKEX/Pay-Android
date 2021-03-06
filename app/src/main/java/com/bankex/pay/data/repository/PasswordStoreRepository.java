package com.bankex.pay.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.widget.Toast;
import com.bankex.pay.R;
import com.bankex.pay.domain.model.PayWalletModel;
import com.bankex.pay.domain.model.ServiceErrorException;
import com.bankex.pay.utils.KeyStoreLocal;
import com.wallet.pwd.trustapp.PasswordManager;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.security.SecureRandom;
import java.util.Map;

/**
 * {@link IPasswordStoreRepository} repository implementation.
 */
public class PasswordStoreRepository implements IPasswordStoreRepository {
	private final Context context;

	public PasswordStoreRepository(Context context) {
		this.context = context;
		migrate();
	}

	/**
	 * Method to save all addresses from {@link SharedPreferences} to {@link KeyStoreLocal}.
	 */
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
					KeyStoreLocal.put(context, address.toLowerCase(), PasswordManager.getPassword(address, context));
				} catch (Exception ex) {
					Toast.makeText(context, R.string.password_store_rep_migration_error, Toast.LENGTH_LONG).show();
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public Single<String> getPassword(PayWalletModel wallet) {
		return Single.fromCallable(() -> {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				return new String(KeyStoreLocal.get(context, wallet.address));
			} else {
				try {
					return PasswordManager.getPassword(wallet.address, context);
				} catch (Exception e) {
					throw new ServiceErrorException(ServiceErrorException.KEY_STORE_ERROR);
				}
			}
		});
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public Completable savePassword(PayWalletModel wallet, String password) {
		return Completable.fromAction(() -> {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				KeyStoreLocal.put(context, wallet.address, password);
			} else {
				try {
					PasswordManager.setPassword(wallet.address, password, context);
				} catch (Exception e) {
					throw new ServiceErrorException(ServiceErrorException.KEY_STORE_ERROR);
				}
			}
		});
	}

	/**
	 * {@inheritDoc }
	 */
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
