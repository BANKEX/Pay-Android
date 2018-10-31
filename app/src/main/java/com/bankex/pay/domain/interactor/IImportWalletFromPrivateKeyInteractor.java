package com.bankex.pay.domain.interactor;


import com.bankex.pay.domain.model.PayWalletModel;
import io.reactivex.Single;

/**
 * {@code Interactor} импорта кошелька по приватному ключу
 *
 * @author Gevork Safaryan on 27.09.2018
 */
public interface IImportWalletFromPrivateKeyInteractor {

    /**
     * Импортировать кошелек по приватному ключу
     *
     * @param privateKey - приватный ключ
     * @param walletName - имя кошелька
     * @return {@link Single} над {@link PayWalletModel}
     */
    Single<PayWalletModel> importStoreByPrivateKey(String privateKey, String walletName);
}
