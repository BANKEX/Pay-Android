package com.bankex.pay.di.walletinfo;

import com.bankex.pay.data.network.BankexRestApi;
import com.bankex.pay.data.network.CryptoCompareRestApi;
import com.bankex.pay.data.reporitories.address.ISearchByAddressRepository;
import com.bankex.pay.data.reporitories.address.SearchByAddressRepository;
import com.bankex.pay.data.reporitories.cryptocompare.CryptoCompareRepository;
import com.bankex.pay.data.reporitories.cryptocompare.ICryptoCompareRepository;
import com.bankex.pay.data.reporitories.transactions.ITransactionListRepository;
import com.bankex.pay.data.reporitories.transactions.TransactionListRepository;
import com.bankex.pay.domain.interactor.address.ISearchByAddressInteractor;
import com.bankex.pay.domain.interactor.address.SearchByAddressInteractor;
import com.bankex.pay.domain.interactor.cryptocompare.ExchangeRateInteractor;
import com.bankex.pay.domain.interactor.cryptocompare.IExchangeRateInteractor;
import com.bankex.pay.domain.interactor.transactions.ITransactionListInteractor;
import com.bankex.pay.domain.interactor.transactions.TransactionListInteractor;
import com.bankex.pay.presentation.presenter.walletinfo.WalletInfoPresenter;
import com.bankex.pay.utils.rx.IRxSchedulersUtils;

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

    @Provides
    @WalletInfoScope
    public ITransactionListInteractor provideTransactionListInteractor(ITransactionListRepository iTransactionListRepository) {
        return new TransactionListInteractor(iTransactionListRepository);
    }

    @Provides
    @WalletInfoScope
    public ISearchByAddressInteractor provideSearchByAddressInteractor(ISearchByAddressRepository searchByAddressRepository) {
        return new SearchByAddressInteractor(searchByAddressRepository);
    }

    @Provides
    @WalletInfoScope
    public ISearchByAddressRepository provideSearchByAddressRepository(BankexRestApi restApi) {
        return new SearchByAddressRepository(restApi);
    }

    @Provides
    @WalletInfoScope
    public IExchangeRateInteractor provideExchangeRateInteractor(ICryptoCompareRepository iCryptoCompareRepository) {
        return new ExchangeRateInteractor(iCryptoCompareRepository);
    }

    @Provides
    @WalletInfoScope
    public ICryptoCompareRepository provideCryptoCompareRepository(CryptoCompareRestApi restApi) {
        return new CryptoCompareRepository(restApi);
    }

    @Provides
    @WalletInfoScope
    public WalletInfoPresenter provideWalletInfoPresenter(ISearchByAddressInteractor iSearchByAddressInteractor, IRxSchedulersUtils iRxSchedulersUtils, IExchangeRateInteractor iExchangeRateInteractor, ITransactionListInteractor transactionListInteractor) {
        return new WalletInfoPresenter(iSearchByAddressInteractor, iExchangeRateInteractor, transactionListInteractor, iRxSchedulersUtils);
    }

}
