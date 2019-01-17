package com.bankex.pay.data.repository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;
import java.io.File;
import java.nio.charset.Charset;
import org.ethereum.geth.Geth;
import org.ethereum.geth.KeyStore;

/**
 * {@link IImportWalletFromKeyStoreRepository} repository implementation.
 */
public class ImportWalletFromKeyStoreRepository implements IImportWalletFromKeyStoreRepository {
	private final KeyStore keyStore;

	/**
	 * Constructor for ImportWalletFromKeyStoreRepository.
	 *
	 * @param keyStoreFile key store file
	 */
	public ImportWalletFromKeyStoreRepository(File keyStoreFile) {
		keyStore = new KeyStore(keyStoreFile.getAbsolutePath(), Geth.LightScryptN, Geth.LightScryptP);
	}

	/**
	 * Method to import wallet, stored in KeyStore.
	 *
	 * @param store String representation of imported wallet
	 * @param password old user password
	 * @param newPassword new user password
	 * @return wallet model as {@link Single}
	 */
	@Override
	public Single<PayWalletModel> importWalletFromKeyStore(String store, String password, String newPassword) {
		return Single.fromCallable(() -> {
			org.ethereum.geth.Account account = keyStore.importKey(store.getBytes(Charset.forName("UTF-8")), password, newPassword);
			return new PayWalletModel(account.getAddress().getHex().toLowerCase());
		});
	}
}
