package com.bankex.pay.data.repository;

import com.bankex.pay.data.realm.IRealmService;
import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;

/**
 * {@link IPayWalletRepository} repository implementation.
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
	public Single<PayWalletModel> saveWallet(PayWalletModel payWalletModel) {
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
