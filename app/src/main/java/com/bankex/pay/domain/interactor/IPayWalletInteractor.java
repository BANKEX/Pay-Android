package com.bankex.pay.domain.interactor;

import com.bankex.pay.model.domain.PayWalletModel;

import javax.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Интерактор для работы с кошельком в БД
 * @author Pavel Apanovskiy on 11/10/2018.
 */
public interface IPayWalletInteractor {
    /**
     * Сохраняем кошелек в БД
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
    Single<PayWalletModel> getWallet();
}
