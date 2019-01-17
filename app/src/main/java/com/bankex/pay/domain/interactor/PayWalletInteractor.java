package com.bankex.pay.domain.interactor;

import com.bankex.pay.data.repository.IPayWalletRepository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;

/**
 * Implementation of {@link IPayWalletInteractor}.
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
    public Single<PayWalletModel> saveWallet(PayWalletModel payWalletModel) {
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
