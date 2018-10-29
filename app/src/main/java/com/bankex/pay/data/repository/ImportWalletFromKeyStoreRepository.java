package com.bankex.pay.data.repository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;
import java.io.File;
import java.nio.charset.Charset;
import org.ethereum.geth.Geth;
import org.ethereum.geth.KeyStore;

/**
 * @author Gevork Safaryan on 04/10/2018
 */
public class ImportWalletFromKeyStoreRepository implements IImportWalletFromKeyStoreRepository {

	private final KeyStore keyStore;

	/**
	 * @param keyStoreFile Файл хранилища
	 */
	public ImportWalletFromKeyStoreRepository(File keyStoreFile) {
		keyStore = new KeyStore(keyStoreFile.getAbsolutePath(), Geth.LightScryptN, Geth.LightScryptP);
	}

	@Override
	public Single<PayWalletModel> importWalletFromKeyStore(String store, String password, String newPassword) {
		return Single.fromCallable(() -> {
			org.ethereum.geth.Account account = keyStore
					.importKey(store.getBytes(Charset.forName("UTF-8")), password, newPassword);
			return new PayWalletModel(account.getAddress().getHex().toLowerCase());
		});
	}
}
