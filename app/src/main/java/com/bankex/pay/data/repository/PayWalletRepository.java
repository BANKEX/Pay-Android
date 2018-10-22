package com.bankex.pay.data.repository;


import com.bankex.pay.data.realm.IRealmService;
import com.bankex.pay.model.domain.PayWalletModel;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Реализация репозитория {@link IPayWalletRepository}
 *
 * @author Pavel Apanovskiy on 11/10/2018.
 */
public class PayWalletRepository implements IPayWalletRepository {

    private final IRealmService mRealmService;

    public PayWalletRepository(IRealmService realmService) {
        mRealmService = realmService;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Completable saveWallet(PayWalletModel payWalletModel) {
        return mRealmService.saveWallet(payWalletModel);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Single<PayWalletModel> getWallet() {
        return mRealmService.getWallet();
    }
}
