package com.bankex.pay.data.repository;


import com.bankex.pay.model.domain.PayWalletModel;

import org.web3j.crypto.Credentials;

import io.reactivex.Single;

/**
 * Репозиторий импорта кошелька по фразе
 *
 * @author Gevork Safaryan on 27/09/2018
 */
public interface IImportWalletFromPassPhraseRepository {

    /**
     * Импортировать Кошелек
     *
     * @param passPhrase - фраза
     * @return обвязка {@link Single} над {@link PayWalletModel}
     */
    Single<Credentials> importWalletFromKeyStore(String passPhrase, String password);
}
