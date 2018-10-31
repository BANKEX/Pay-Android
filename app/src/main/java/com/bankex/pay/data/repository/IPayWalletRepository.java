package com.bankex.pay.data.repository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;
import javax.annotation.Nullable;

/**
 * Репозиторий работы с кошельком в БД
 *
 * @author Pavel Apanovskiy on 11/10/2018.
 */
public interface IPayWalletRepository {

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
	@Nullable
	Single<PayWalletModel> getWallet();
}
