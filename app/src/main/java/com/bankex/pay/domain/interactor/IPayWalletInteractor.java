package com.bankex.pay.domain.interactor;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;

/**
 * Interface to work with user wallet saved in database.
 */
public interface IPayWalletInteractor {

	/**
	 * Method to save wallet in database.
	 *
	 * @param payWalletModel wallet to save
	 * @return model of saved wallet
	 */
	Single<PayWalletModel> saveWallet(PayWalletModel payWalletModel);

	/**
	 * Method to get wallet from database.
	 *
	 * @return model of saved wallet
	 */
	Single<PayWalletModel> getWallet();
}
