package com.bankex.pay.domain.interactor;

import android.support.annotation.NonNull;
import com.bankex.pay.data.repository.IImportWalletFromKeyStoreRepository;
import com.bankex.pay.data.repository.IImportWalletFromPassPhraseRepository;
import com.bankex.pay.data.repository.IPasswordStoreRepository;
import com.bankex.pay.data.repository.IPayWalletRepository;
import com.bankex.pay.domain.model.PayWalletModel;
import dagger.internal.Preconditions;
import io.reactivex.Single;

/**
 * Implementation for {@link IImportWalletByPassPhraseInteractor}.
 */
public class ImportWalletByPassPhraseInteractor implements IImportWalletByPassPhraseInteractor {
	private final IImportWalletFromPassPhraseRepository mImportWalletFromPassPhraseRepository;
	private final IImportWalletFromKeyStoreRepository mImportWalletFromKeyStoreRepository;
	private final IPasswordStoreRepository mPasswordStoreRepository;
	private final IPayWalletRepository mPayWalletRepository;

	/**
	 * @param importWalletFromPassPhraseRepository {@link IImportWalletFromPassPhraseRepository}
	 * @param importWalletFromKeyStoreRepository {@link IImportWalletFromKeyStoreRepository}
	 * @param passwordStoreRepository {@link IPasswordStoreRepository}
	 */
	public ImportWalletByPassPhraseInteractor(
			@NonNull IImportWalletFromPassPhraseRepository importWalletFromPassPhraseRepository,
			@NonNull IImportWalletFromKeyStoreRepository importWalletFromKeyStoreRepository,
			@NonNull IPasswordStoreRepository passwordStoreRepository,
			@NonNull IPayWalletRepository payWalletRepository) {
		mImportWalletFromPassPhraseRepository = Preconditions.checkNotNull(
				importWalletFromPassPhraseRepository, "IImportWalletFromPassPhraseRepository must be not null");
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
	public Single<PayWalletModel> importWalletByPassPhrase(String passPhrase, String walletName) {
		return mPasswordStoreRepository.generatePassword()
				.flatMap(password ->
						mImportWalletFromPassPhraseRepository.importWalletFromKeyStore(passPhrase, password))
				.flatMap(credentials ->
						Single.fromCallable(() ->
								new PayWalletModel(credentials.getAddress(), walletName)))
				.flatMap(mPayWalletRepository::saveWallet);
	}
}
