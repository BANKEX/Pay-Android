package com.bankex.pay.data.repository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Interface for password storage repository.
 */
public interface IPasswordStoreRepository {

	/**
	 * Method to get password for entered wallet.
	 *
	 * @param wallet wallet which password is needed
	 * @return password as {@link Single}
	 */
	Single<String> getPassword(PayWalletModel wallet);

	/**
	 * Method to save pair: wallet and its` password.
	 *
	 * @param wallet {@link PayWalletModel}
	 * @param password user password
	 * @return {@link Completable}
	 */
	Completable savePassword(PayWalletModel wallet, String password);

	/**
	 * Method to generate password for wallet.
	 *
	 * @return password as {@link Single} {@link String}
	 */
	Single<String> generatePassword();
}
