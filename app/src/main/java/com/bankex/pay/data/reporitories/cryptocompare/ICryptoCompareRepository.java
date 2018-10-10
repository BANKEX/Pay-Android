package com.bankex.pay.data.reporitories.cryptocompare;

import com.bankex.pay.data.entity.TickerEntity;
import com.bankex.pay.domain.models.network.BaseHeadResponse;

import io.reactivex.Single;

/**
 * Базовый интерфейс для получения курса криптовалюты с ресурса CryptoCompare
 *
 * @author Denis Anisimov.
 */
public interface ICryptoCompareRepository {
    Single<BaseHeadResponse<TickerEntity>> loadExchangeUSDRate(String ticker);
}
