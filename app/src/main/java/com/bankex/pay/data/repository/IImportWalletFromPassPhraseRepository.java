package com.bankex.pay.data.repository;

import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;
import org.web3j.crypto.Credentials;

/**
 * Репозиторий импорта кошелька по фразе
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
