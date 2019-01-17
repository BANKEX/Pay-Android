package com.bankex.pay.data.repository;

import io.reactivex.Single;

/**
 * Interface for repository to import wallet by private key.
 */
public interface IImportWalletFromPrivateKeyRepository {

	/**
	 * Import Ethereum wallet file.
	 *
	 * @param privateKey user private key
	 * @param password user password
	 * @return wallet in String format
	 */
	Single<String> importStoreByPrivateKey(String privateKey, final String password);
}
