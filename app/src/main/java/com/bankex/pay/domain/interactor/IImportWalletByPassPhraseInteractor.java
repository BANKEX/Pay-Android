package com.bankex.pay.domain.interactor;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;

/**
 * Interface for operations with wallet import by password phrase.
 */
public interface IImportWalletByPassPhraseInteractor {

	/**
	 * Method to import wallet by password phrase.
	 *
	 * @param passPhrase user secret password phrase
	 * @param walletName custom name of wallet to import
	 * @return imported wallet as{@link Single}
	 */
	Single<PayWalletModel> importWalletByPassPhrase(String passPhrase, String walletName);
}
