package com.bankex.pay.data.repository;

import io.reactivex.Single;
import org.web3j.crypto.Credentials;

/**
 * Interface for repository to import wallet from key store by password-phrase.
 */
public interface IImportWalletFromPassPhraseRepository {

	/**
	 * Import the wallet.
	 *
	 * @param passPhrase secret password-phrase
	 * @return credentials for import operation
	 */
	Single<Credentials> importWalletFromKeyStore(String passPhrase, String password);
}
