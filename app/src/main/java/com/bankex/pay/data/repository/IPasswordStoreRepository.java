package com.bankex.pay.data.repository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Интерфейс репозитория паролей
 *
 * @author Gevork Safaryan on 27.09.2018
 */
public interface IPasswordStoreRepository {

	/**
	 * Получить пароль для кошелька
	 *
	 * @param wallet - кошелек
	 * @return {@link Single} над {@link String}
	 */
	Single<String> loadPassword(PayWalletModel wallet);

	/**
	 * Сохранить пароль для кошелька
	 *
	 * @param wallet - кошелек
	 * @param password - пароль
	 * @return {@link Completable}
	 */
	Completable savePassword(PayWalletModel wallet, String password);

	/**
	 * Сгенерировать пароль
	 *
	 * @return {@link Single} над {@link String}
	 */
	Single<String> generatePassword();
}
