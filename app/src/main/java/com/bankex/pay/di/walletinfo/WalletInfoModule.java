package com.bankex.pay.di.walletinfo;

import com.bankex.pay.data.network.BankexRestApi;
import com.bankex.pay.data.reporitories.ITransactionListRepository;
import com.bankex.pay.data.reporitories.TransactionListRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль экрана продробностей кошелька
 *
 * @author Denis Anisimov on 05.10.2018.
 */
@Module
public class WalletInfoModule {

    @Provides
    @WalletInfoScope
    public ITransactionListRepository provideTransactionListRepository(BankexRestApi restApi) {
        return new TransactionListRepository(restApi);
    }
}
