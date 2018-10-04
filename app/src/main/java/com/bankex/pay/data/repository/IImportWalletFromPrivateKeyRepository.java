package com.bankex.pay.data.repository;

import org.web3j.crypto.Wallet;

import io.reactivex.Single;

/**
 * Репозиторий импорта кошелька по ключу
 *
 * @author Gevork Safaryan on 27/09/2018
 */
public interface IImportWalletFromPrivateKeyRepository {

    /**
     * Импортировать строку
     *
     * @param privateKey приватный ключ
     * @param password   пароль
     * @return обвязка {@link Single} над {@link Wallet}
     */
    Single<String> importStoreByPrivateKey(String privateKey, final String password);
}
