package com.bankex.pay.domain.interactor;

import com.bankex.pay.model.domain.PayWalletModel;

import io.reactivex.Single;

/**
 * {@code Interactor} импорта кошелька по фразе
 *
 * @author Gevork Safaryan on 27.09.2018
 */
public interface IImportWalletFromPassPhraseInteractor {

    /**
     * Импортировать кошелек по фразе
     *
     * @param passPhrase - фраза
     * @param walletName - имя кошелька
     * @return {@link Single} над {@link PayWalletModel}
     */
    Single<PayWalletModel> importWalletFromPassPhrase(String passPhrase, String walletName);
}
