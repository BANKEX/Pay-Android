package com.bankex.pay.domain.interactor;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;

/**
 * Интерактор для работы с кошельком в БД
 *
 * @author Pavel Apanovskiy on 11/10/2018.
 */
public interface IPayWalletInteractor {
	/**
	 * Сохраняем кошелек в БД
	 *
	 * @param payWalletModel кошелек
	 * @return Completable
	 */
	Single<PayWalletModel> saveWallet(PayWalletModel payWalletModel);

	/**
	 * Пытаемся получить кошелек из БД
	 *
	 * @return кошелек
	 */
	Single<PayWalletModel> getWallet();
}
