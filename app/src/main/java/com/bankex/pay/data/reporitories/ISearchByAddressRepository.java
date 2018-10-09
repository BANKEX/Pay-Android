package com.bankex.pay.data.reporitories;

import android.support.annotation.Nullable;

import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.models.BaseBankexModel;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Gevork Safaryan on 18.06.2018
 */
public interface ISearchByAddressRepository {

    /**
     * Получить результат поиска по адресу
     *
     * @param address - адрес
     * @return {@link Single} над {@link List <  BaseBankexModel  >>}
     */
    Single<BaseDomainBean<List<BaseBankexModel>>> findByAddress(@Nullable String address);
}
