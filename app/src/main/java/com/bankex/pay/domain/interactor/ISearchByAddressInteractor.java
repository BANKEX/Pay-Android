package com.bankex.pay.domain.interactor;

import android.support.annotation.Nullable;

import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.models.BaseBankexModel;

import java.util.List;

import io.reactivex.Single;

/**
 * {@code Interactor} для поиска значений по адресу
 *
 * @author Gevork Safaryan on 16.06.2018
 */
public interface ISearchByAddressInteractor {

    /**
     * Получить результат поиска по адресу
     *
     * @param address - адрес
     * @return {@link Single} над {@link BaseDomainBean< List < BaseBankexModel >>}
     */
    Single<BaseDomainBean<List<BaseBankexModel>>> findByAddress(@Nullable String address);
}
