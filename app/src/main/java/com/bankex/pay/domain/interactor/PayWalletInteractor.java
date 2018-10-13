package com.bankex.pay.domain.interactor;

import com.bankex.pay.data.repository.IPayWalletRepository;
import com.bankex.pay.model.domain.PayWalletModel;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * @author Pavel Apanovskiy on 11/10/2018.
 */
public class PayWalletInteractor implements IPayWalletInteractor {

    private final IPayWalletRepository mSaveWalletRepository;

    public PayWalletInteractor(IPayWalletRepository saveWalletRepository) {
        mSaveWalletRepository = saveWalletRepository;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Completable saveWallet(PayWalletModel payWalletModel) {
        return mSaveWalletRepository.saveWallet(payWalletModel);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Single<PayWalletModel> getWallet() {
        return mSaveWalletRepository.getWallet();
    }
}
