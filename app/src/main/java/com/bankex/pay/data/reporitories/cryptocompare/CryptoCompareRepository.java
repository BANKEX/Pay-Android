package com.bankex.pay.data.reporitories.cryptocompare;

import com.bankex.pay.data.entity.TickerEntity;
import com.bankex.pay.data.entity.mappers.AddressReponseToAddressBeanConverter;
import com.bankex.pay.data.network.CryptoCompareRestApi;
import com.bankex.pay.domain.models.network.BaseHeadResponse;

import io.reactivex.Single;

/**
 * Репозиторий поиска по адресу
 *
 * @author Denis Anisimov on 08.10.2018
 */
public class CryptoCompareRepository implements ICryptoCompareRepository {

    private CryptoCompareRestApi mRestApi;

    private final AddressReponseToAddressBeanConverter mAddressReponseToAddressBeanConverter;

    public CryptoCompareRepository(CryptoCompareRestApi restApi) {
        mRestApi = restApi;
        mAddressReponseToAddressBeanConverter = new AddressReponseToAddressBeanConverter();
    }

    /**
     * Получить обменный курс
     *
     * @param ticker тикер токена
     * @return {@link Single} над {@link BaseHeadResponse}
     */
    @Override
    public Single<BaseHeadResponse<TickerEntity>> loadExchangeUSDRate(String ticker) {
        return mRestApi.fetchTickerPrice(ticker);
    }
}
