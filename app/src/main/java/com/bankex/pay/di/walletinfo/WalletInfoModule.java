package com.bankex.pay.di.walletinfo;

import com.bankex.pay.data.network.BankexRestApi;
import com.bankex.pay.data.reporitories.ISearchByAddressRepository;
import com.bankex.pay.data.reporitories.ITransactionListRepository;
import com.bankex.pay.data.reporitories.SearchByAddressRepository;
import com.bankex.pay.data.reporitories.TransactionListRepository;
import com.bankex.pay.domain.interactor.ISearchByAddressInteractor;
import com.bankex.pay.domain.interactor.SearchByAddressInteractor;
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
    public WalletInfoPresenter provideWalletInfoPresenter(ISearchByAddressInteractor iSearchByAddressInteractor, IRxSchedulersUtils iRxSchedulersUtils) {
        return new WalletInfoPresenter(iSearchByAddressInteractor, iRxSchedulersUtils);
    }

}
