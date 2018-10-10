package com.bankex.pay.data.network;

import com.bankex.pay.data.entity.TickerEntity;
import com.bankex.pay.domain.models.network.BaseHeadResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Denis Anisimov.
 */
public interface CryptoCompareRestApi {

    @GET("/data/price?fsym={ticker}&tsyms=USD")
    Single<BaseHeadResponse<TickerEntity>> fetchTickerPrice(@Path("ticker") String ticker);
}
