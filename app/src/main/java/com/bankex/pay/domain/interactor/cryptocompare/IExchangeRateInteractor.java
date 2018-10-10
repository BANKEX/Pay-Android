package com.bankex.pay.domain.interactor.cryptocompare;

import android.support.annotation.Nullable;

import com.bankex.pay.data.entity.TickerEntity;
import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.network.BaseHeadResponse;

import io.reactivex.Single;

/**
 * {@code Interactor} для обменного курса токена криптовалюты
 *
 * @author Denis Anisimov on 10.10.2018
 */
public interface IExchangeRateInteractor {

    /**
     * Получить результат поиска по адресу
     *
     * @param tiker - тикер криптовалюты чей курс чей текущий курс мы хотим узнать.
     * @return {@link Single} над {@link BaseHeadResponse< BaseBankexModel >}
     */
    Single<BaseHeadResponse<TickerEntity>> getExhangeRateToUSD(@Nullable String tiker);
}
