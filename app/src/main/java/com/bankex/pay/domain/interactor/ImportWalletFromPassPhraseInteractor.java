package com.bankex.pay.domain.interactor;

import android.support.annotation.NonNull;

import com.bankex.pay.data.repository.IImportWalletFromKeyStoreRepository;
import com.bankex.pay.data.repository.IImportWalletFromPassPhraseRepository;
import com.bankex.pay.data.repository.IPasswordStoreRepository;
import com.bankex.pay.model.domain.PayWalletModel;

import dagger.internal.Preconditions;
import io.reactivex.Single;

/**
 * {@code Interactor} импорта кошелька по фразе
 *
 * @author Gevork Safaryan on 27.09.2018
 */
public class ImportWalletFromPassPhraseInteractor implements IImportWalletFromPassPhraseInteractor {

    private final IImportWalletFromPassPhraseRepository mImportWalletFromPassPhraseRepository;
    private final IImportWalletFromKeyStoreRepository mImportWalletFromKeyStoreRepository;
    private final IPasswordStoreRepository mPasswordStoreRepository;

    /**
     * @param importWalletFromPassPhraseRepository {@link IImportWalletFromPassPhraseRepository}
     * @param importWalletFromKeyStoreRepository   {@link IImportWalletFromKeyStoreRepository}
     * @param passwordStoreRepository              {@link IPasswordStoreRepository}
     */
    public ImportWalletFromPassPhraseInteractor(@NonNull IImportWalletFromPassPhraseRepository importWalletFromPassPhraseRepository,
                                                @NonNull IImportWalletFromKeyStoreRepository importWalletFromKeyStoreRepository,
                                                @NonNull IPasswordStoreRepository passwordStoreRepository) {
        mImportWalletFromPassPhraseRepository = Preconditions.checkNotNull(
                importWalletFromPassPhraseRepository,
                "IImportWalletFromPassPhraseRepository must be not null");
        mImportWalletFromKeyStoreRepository = Preconditions.checkNotNull(
                importWalletFromKeyStoreRepository,
                "IImportWalletFromKeyStoreRepository must be not null");
        mPasswordStoreRepository = Preconditions.checkNotNull(
                passwordStoreRepository,
                "IPasswordStoreRepository must be not null");
    }

    /**
     * Импортировать кошелек по фразе
     *
     * @param passPhrase - фраза
     * @param walletName - имя кошелька
     * @return {@link Single} над {@link PayWalletModel}
     */
    @Override
    public Single<PayWalletModel> importWalletFromPassPhrase(String passPhrase, String walletName) {
        return mPasswordStoreRepository.generatePassword()
                .flatMap(password -> mImportWalletFromPassPhraseRepository.importWalletFromKeyStore(passPhrase, password))
                .flatMap(credentials -> Single.fromCallable(() -> new PayWalletModel(credentials.getAddress(), walletName)));
    }
}
