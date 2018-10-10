package com.bankex.pay.domain.interactor.address;

import android.support.annotation.Nullable;

import com.bankex.pay.data.reporitories.address.ISearchByAddressRepository;
import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.models.BaseBankexModel;

import java.util.List;

import io.reactivex.Single;

/**
 * {@code Interactor} для поиска значений по адресу
 *
 * @author Gevork Safaryan on 16.06.2018
 */
public class SearchByAddressInteractor implements ISearchByAddressInteractor {

    private ISearchByAddressRepository mSearchByAddressRepository;

    public SearchByAddressInteractor(ISearchByAddressRepository searchByAddressRepository) {
        mSearchByAddressRepository = searchByAddressRepository;
    }

    @Override
    public Single<BaseDomainBean<List<BaseBankexModel>>> findByAddress(@Nullable String hash) {
        return mSearchByAddressRepository.findByAddress(hash);
    }
}
