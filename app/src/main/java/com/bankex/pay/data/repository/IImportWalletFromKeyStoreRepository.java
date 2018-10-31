package com.bankex.pay.data.repository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;

/**
 * Репозиторий импорта кошелька по ключу
 *
 * @author Gevork Safaryan on 27/09/2018
 */
public interface IImportWalletFromKeyStoreRepository {

	/**
	 * Импортировать Кошелек
	 *
	 * @param store хранилище
	 * @param password старый пароль
	 * @param newPassword новый пароль
	 * @return обвязка {@link Single} над {@link PayWalletModel}
	 */
	Single<PayWalletModel> importWalletFromKeyStore(String store, String password, String newPassword);
}
