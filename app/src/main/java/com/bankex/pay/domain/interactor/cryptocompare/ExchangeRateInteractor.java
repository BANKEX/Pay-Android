package com.bankex.pay.domain.interactor.cryptocompare;

import android.support.annotation.Nullable;

import com.bankex.pay.data.entity.TickerEntity;
import com.bankex.pay.data.reporitories.cryptocompare.ICryptoCompareRepository;
import com.bankex.pay.domain.models.network.BaseHeadResponse;

import io.reactivex.Single;

/**
 * {@code Interactor} для поиска значений по адресу
 *
 * @author Denis Anisimov on 10.10.2018
 */
public class ExchangeRateInteractor implements IExchangeRateInteractor {

    public ExchangeRateInteractor(ICryptoCompareRepository ICryptoCompareRepository) {
        mICryptoCompareRepository = ICryptoCompareRepository;
    }

    private ICryptoCompareRepository mICryptoCompareRepository;


    @Override
    public Single<BaseHeadResponse<TickerEntity>> getExhangeRateToUSD(@Nullable String tiker) {
        return mICryptoCompareRepository.loadExchangeUSDRate(tiker);
    }
}
