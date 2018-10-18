package com.bankex.pay.data.repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletFile;

import java.math.BigInteger;

import io.reactivex.Single;

import static org.web3j.crypto.Wallet.create;

/**
 * @author Gevork Safaryan on 04/10/2018
 */
public class ImportWalletFromPrivateKeyRepository implements IImportWalletFromPrivateKeyRepository {

    private static final int PRIVATE_KEY_RADIX = 16;

    private static final int N = 1 << 9;
    private static final int P = 1;

    @Override
    public Single<String> importStoreByPrivateKey(String privateKey, final String password) {
        return Single.fromCallable(() -> {
            BigInteger key = new BigInteger(privateKey, PRIVATE_KEY_RADIX);
            ECKeyPair keypair = ECKeyPair.create(key);
            WalletFile walletFile = create(password, keypair, N, P);
            org.web3j.crypto.Wallet wallet = new org.web3j.crypto.Wallet();
            return new ObjectMapper().writeValueAsString(walletFile);
        });
    }
}
