package com.bankex.pay.data.realm;

import com.bankex.pay.model.domain.PayWalletModel;

import javax.annotation.Nullable;

import io.reactivex.Single;

/**
 * Интерфейс работы с базой данных
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public interface IRealmService {

    /**
     * Сохраняем кошелек в БД
     *
     * @param payWalletModel кошелек
     * @return Single<PayWalletModel>
     */
    Single<PayWalletModel>  saveWallet(PayWalletModel payWalletModel);

    /**
     * Пытаемся получить кошелек из БД
     *
     * @return кошелек
     */
    @Nullable
    Single<PayWalletModel> getWallet();

    /**
     * Закрыть соединение
     */
    void closeRealm();
}
