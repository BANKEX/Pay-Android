package com.bankex.pay.domain.interactor;

import android.support.annotation.NonNull;

import com.bankex.pay.data.repository.IImportWalletFromKeyStoreRepository;
import com.bankex.pay.data.repository.IImportWalletFromPrivateKeyRepository;
import com.bankex.pay.data.repository.IPasswordStoreRepository;
import com.bankex.pay.model.domain.PayWalletModel;

import dagger.internal.Preconditions;
import io.reactivex.Single;

/**
 * {@code Interactor} импорта кошелька по приватному ключу
 *
 * @author Gevork Safaryan on 27.09.2018
 */
public class ImportWalletFromPrivateKeyInteractor implements IImportWalletFromPrivateKeyInteractor {

    private final IImportWalletFromPrivateKeyRepository mImportWalletFromPrivateKeyRepository;
    private final IImportWalletFromKeyStoreRepository mImportWalletFromKeyStoreRepository;
    private final IPasswordStoreRepository mPasswordStoreRepository;

    /**
     * @param importWalletFromPrivateKeyRepository {@link IImportWalletFromPrivateKeyRepository}
     * @param importWalletFromKeyStoreRepository   {@link IImportWalletFromKeyStoreRepository}
     * @param passwordStoreRepository              {@link IPasswordStoreRepository}
     */
    public ImportWalletFromPrivateKeyInteractor(@NonNull IImportWalletFromPrivateKeyRepository importWalletFromPrivateKeyRepository,
                                                @NonNull IImportWalletFromKeyStoreRepository importWalletFromKeyStoreRepository,
                                                @NonNull IPasswordStoreRepository passwordStoreRepository) {
        mImportWalletFromPrivateKeyRepository = Preconditions.checkNotNull(
                importWalletFromPrivateKeyRepository,
                "IImportWalletFromPrivateKeyRepository must be not null");
        mImportWalletFromKeyStoreRepository = Preconditions.checkNotNull(
                importWalletFromKeyStoreRepository,
                "IImportWalletFromKeyStoreRepository must be not null");
        mPasswordStoreRepository = Preconditions.checkNotNull(
                passwordStoreRepository,
                "IPasswordStoreRepository must be not null");
    }

    /**
     * Импортировать кошелек по приватному ключу
     *
     * @param privateKey - приватный ключ
     * @param walletName - имя кошелька
     * @return {@link Single} над {@link PayWalletModel}
     */
    @Override
    public Single<PayWalletModel> importStoreByPrivateKey(String privateKey, String walletName) {
        return mPasswordStoreRepository.generatePassword()
                .flatMap(password -> mImportWalletFromPrivateKeyRepository.importStoreByPrivateKey(privateKey, password)
                        .flatMap(store -> mImportWalletFromKeyStoreRepository.importWalletFromKeyStore(store, password, password)))
                .flatMap(payWalletModel -> Single.fromCallable(() -> payWalletModel.setName(walletName)));
    }
}
