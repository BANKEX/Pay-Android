package com.bankex.pay.data.repository;

import com.bankex.pay.model.domain.PayWalletModel;

import javax.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.Maybe;

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
    Completable saveWallet(PayWalletModel payWalletModel);

    /**
     * Пытаемся получить кошелек из БД
     *
     * @return кошелек
     */
    @Nullable
    Maybe<PayWalletModel> getWallet();
}
