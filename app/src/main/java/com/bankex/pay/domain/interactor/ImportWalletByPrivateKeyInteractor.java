package com.bankex.pay.domain.interactor;

import android.support.annotation.NonNull;
import com.bankex.pay.data.repository.IImportWalletFromKeyStoreRepository;
import com.bankex.pay.data.repository.IImportWalletFromPrivateKeyRepository;
import com.bankex.pay.data.repository.IPasswordStoreRepository;
import com.bankex.pay.data.repository.IPayWalletRepository;
import com.bankex.pay.domain.model.PayWalletModel;
import dagger.internal.Preconditions;
import io.reactivex.Single;

/**
 * Implementation for {@link IImportWalletByPrivateKeyInteractor}.
 */
public class ImportWalletByPrivateKeyInteractor implements IImportWalletByPrivateKeyInteractor {
	private final IImportWalletFromPrivateKeyRepository mImportWalletFromPrivateKeyRepository;
	private final IImportWalletFromKeyStoreRepository mImportWalletFromKeyStoreRepository;
	private final IPasswordStoreRepository mPasswordStoreRepository;
	private final IPayWalletRepository mPayWalletRepository;

	/**
	 * @param importWalletFromPrivateKeyRepository {@link IImportWalletFromPrivateKeyRepository}
	 * @param importWalletFromKeyStoreRepository {@link IImportWalletFromKeyStoreRepository}
	 * @param passwordStoreRepository {@link IPasswordStoreRepository}
	 */
	public ImportWalletByPrivateKeyInteractor(
			@NonNull IImportWalletFromPrivateKeyRepository importWalletFromPrivateKeyRepository,
			@NonNull IImportWalletFromKeyStoreRepository importWalletFromKeyStoreRepository,
			@NonNull IPasswordStoreRepository passwordStoreRepository,
			@NonNull IPayWalletRepository payWalletRepository) {
		mImportWalletFromPrivateKeyRepository = Preconditions.checkNotNull(
				importWalletFromPrivateKeyRepository, "IImportWalletFromPrivateKeyRepository must be not null");
		mImportWalletFromKeyStoreRepository = Preconditions.checkNotNull(
				importWalletFromKeyStoreRepository, "IImportWalletFromKeyStoreRepository must be not null");
		mPasswordStoreRepository = Preconditions.checkNotNull(
				passwordStoreRepository, "IPasswordStoreRepository must be not null");
		mPayWalletRepository = Preconditions.checkNotNull(
				payWalletRepository, "IPayWalletRepository must be not null");
	}

	/**
	 * {@inheritDoc }
	 */
	@Override
	public Single<PayWalletModel> importWalletByPrivateKey(String privateKey, String walletName) {
		return mPasswordStoreRepository.generatePassword()
				.flatMap(password ->
						mImportWalletFromPrivateKeyRepository
								.importStoreByPrivateKey(privateKey, password)
								.flatMap(store ->
										mImportWalletFromKeyStoreRepository.importWalletFromKeyStore(store, password, password)))
				.flatMap(payWalletModel -> Single.fromCallable(() -> payWalletModel.setName(walletName)))
				.doAfterSuccess(mPayWalletRepository::saveWallet);
	}
}
