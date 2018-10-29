package com.bankex.pay.data.realm;

import com.bankex.pay.domain.model.PayWalletModel;
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
    public Single<PayWalletModel> saveWallet(PayWalletModel payWalletModel) {
        return Single.fromCallable(() -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(payWalletModel);
            realm.commitTransaction();
            return payWalletModel;
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
