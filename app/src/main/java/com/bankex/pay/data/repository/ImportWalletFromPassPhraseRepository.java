package com.bankex.pay.data.repository;

import io.reactivex.Single;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

/**
 * {@link IImportWalletFromPassPhraseRepository} repository implementation.
 */
public class ImportWalletFromPassPhraseRepository implements IImportWalletFromPassPhraseRepository {

	/**
	 * {@inheritDoc }
	 */
	@Override
	public Single<Credentials> importWalletFromKeyStore(String passPhrase, String password) {
		return Single.fromCallable(() -> WalletUtils.loadBip39Credentials(password, passPhrase));
	}
}
