package com.bankex.pay.data.repository;

import com.bankex.pay.model.domain.PayWalletModel;

import javax.annotation.Nullable;

import io.reactivex.Single;

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
