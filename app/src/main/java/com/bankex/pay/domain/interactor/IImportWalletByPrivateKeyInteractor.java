package com.bankex.pay.domain.interactor;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;

/**
 * Interface for import wallet by user private key.
 */
public interface IImportWalletByPrivateKeyInteractor {

	/**
	 * Method to import wallet by private key.
	 *
	 * @param privateKey user private key
	 * @param walletName name for imported wallet
	 * @return {@link Single} над {@link PayWalletModel}
	 */
	Single<PayWalletModel> importWalletByPrivateKey(String privateKey, String walletName);
}
