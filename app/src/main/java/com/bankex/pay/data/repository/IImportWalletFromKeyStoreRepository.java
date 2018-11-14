package com.bankex.pay.data.repository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;

/**
 * Interface for repository for wallet import by key.
 */
public interface IImportWalletFromKeyStoreRepository {

	/**
	 * Method to import the wallet.
	 *
	 * @param store String representation of imported wallet
	 * @param password old user password
	 * @param newPassword new user password
	 * @return wallet model as {@link Single}
	 */
	Single<PayWalletModel> importWalletFromKeyStore(String store, String password, String newPassword);
}
