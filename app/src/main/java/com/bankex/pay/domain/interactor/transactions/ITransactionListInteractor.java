package com.bankex.pay.domain.interactor.transactions;

import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.models.transaction.ListType;
import com.bankex.pay.domain.models.transaction.ModuleDestinationType;
import com.bankex.pay.domain.models.transaction.TransactionModel;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Gevork Safaryan on 22.06.2018
 */
public interface ITransactionListInteractor {

    /**
     * @param listtype
     * @param moduleDestinationType
     * @param id
     * @param page
     * @param size
     * @return
     */
    Single<BaseDomainBean<List<TransactionModel>>> loadTransactions(ListType listtype, ModuleDestinationType moduleDestinationType, String id, int page, int size);
}
