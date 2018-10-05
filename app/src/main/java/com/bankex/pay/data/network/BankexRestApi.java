package com.bankex.pay.data.network;

import com.bankex.pay.domain.models.network.AddressResponse;
import com.bankex.pay.domain.models.network.BaseHeadResponse;
import com.bankex.pay.domain.models.network.BaseResponse;
import com.bankex.pay.domain.models.network.BlockResponse;
import com.bankex.pay.domain.models.network.HashRequest;
import com.bankex.pay.domain.models.network.HashResponse;
import com.bankex.pay.domain.models.network.Token;
import com.bankex.pay.domain.models.network.TransactionHeadResponse;
import com.bankex.pay.domain.models.network.TransactionRequest;
import com.bankex.pay.domain.models.network.TransactionResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Интерфейс работи с API
 *
 * @author Gevork Safaryan on 29.05.2018
 */
public interface BankexRestApi {

    @POST("txdetails")
    Single<BaseResponse<List<HashResponse>, HashResponse>> findByHesh(@Body HashRequest hashRequest);

    @GET("v2/address/details")
    Single<BaseHeadResponse<AddressResponse>> findByAddress(@Query("addr") String address);

    @GET("v2/block/details")
    Single<BaseHeadResponse<BlockResponse>> findByBlock(@Query("block") String blockNumber);

    @POST("list")
    Single<BaseResponse<List<TransactionResponse>, TransactionHeadResponse>> loadTransaction(@Body TransactionRequest transactionRequest);

    @POST("list")
    Single<BaseResponse<List<Token>, TransactionHeadResponse>> loadTokens(@Body TransactionRequest transactionRequest);
}
