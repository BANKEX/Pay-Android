package com.bankex.pay.data.repository;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import io.reactivex.Single;

/**
 * Рапозиторий Импорта Кошелька по фразе
 *
 * @author Gevork Safaryan on 04/10/2018
 */
public class ImportWalletFromPassPhraseRepository implements IImportWalletFromPassPhraseRepository {

    /**
     * Импортировать Кошелек
     *
     * @param passPhrase - фраза
     * @return обвязка {@link Single} над {@link Credentials}
     */
    @Override
    public Single<Credentials> importWalletFromKeyStore(String passPhrase, String password) {
        return Single.fromCallable(() -> WalletUtils.loadBip39Credentials(password, passPhrase));
    }
}
