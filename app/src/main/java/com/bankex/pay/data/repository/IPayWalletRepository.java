package com.bankex.pay.data.repository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;
import javax.annotation.Nullable;

/**
 * Interface for wallet saved in database.
 */
public interface IPayWalletRepository {

	/**
	 * Method to save wallet into database.
	 *
	 * @param payWalletModel wallet to save
	 * @return saved wallet as {@link Single}
	 */
	Single<PayWalletModel> saveWallet(PayWalletModel payWalletModel);

	/**
	 * Method to get wallet from database.
	 *
	 * @return if there is one wallet, we get it
	 */
	@Nullable
	Single<PayWalletModel> getWallet();
}
