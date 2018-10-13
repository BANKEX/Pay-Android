package com.bankex.pay.data.realm;

import com.bankex.pay.model.domain.PayWalletModel;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.realm.Realm;

/**
 * Реализация {@link DefaultRealmService}
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class DefaultRealmService implements IRealmService {
    /**
     * {@inheritDoc }
     */
    @Override
    public Completable saveWallet(PayWalletModel payWalletModel) {
        return Completable.fromCallable(() -> {
            Realm realm = Realm.getDefaultInstance();
            if (realm.where(PayWalletModel.class).equalTo("address", payWalletModel.getAddress()).findFirst() == null) {
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(payWalletModel);
                realm.commitTransaction();
            }
            return null;
        });
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Single<PayWalletModel> getWallet() {
        return Single.fromCallable(() -> {
            PayWalletModel payWalletModel = Realm.getDefaultInstance().where(PayWalletModel.class).findFirst();
            PayWalletModel walletModel = null;
            if (payWalletModel != null) {
                walletModel = new PayWalletModel(payWalletModel.getAddress(), payWalletModel.getName());
                walletModel.setKey(payWalletModel.getKey());
            }
            return walletModel;
        });
    }

    @Override
    public void closeRealm() {
        //todo
    }
}
