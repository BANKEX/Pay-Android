package com.bankex.pay.data.reporitories;

import android.support.annotation.Nullable;


import com.bankex.pay.data.entity.mappers.AddressReponseToAddressBeanConverter;
import com.bankex.pay.data.network.BankexRestApi;
import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.network.AddressRequest;
import com.bankex.pay.domain.models.network.AddressResponse;
import com.bankex.pay.domain.models.network.BaseHeadResponse;

import java.util.List;

import io.reactivex.Single;

/**
 * Репозиторий поиска по адресу
 *
 * @author Gevork Safaryan on 18.06.2018
 */
public class SearchByAddressRepository implements ISearchByAddressRepository {

    private BankexRestApi mRestApi;

    private final AddressReponseToAddressBeanConverter mAddressReponseToAddressBeanConverter;

    public SearchByAddressRepository(BankexRestApi restApi) {
        mRestApi = restApi;
        mAddressReponseToAddressBeanConverter = new AddressReponseToAddressBeanConverter();
    }

    @Override
    public Single<BaseDomainBean<List<BaseBankexModel>>> findByAddress(@Nullable String address) {
        AddressRequest hashRequest = new AddressRequest(address);
        return loadOperationsFromNetwork(hashRequest).map(mAddressReponseToAddressBeanConverter::convert);
    }

    /**
     * Получить результат поиска по хешу из сети
     *
     * @param request модель запроса для получения результата поиска по хешу
     * @return {@link Single} над {@link BaseHeadResponse}
     */
    private Single<BaseHeadResponse<AddressResponse>> loadOperationsFromNetwork(final AddressRequest request) {
        return mRestApi.findByAddress(request.getAddr());
    }
}
