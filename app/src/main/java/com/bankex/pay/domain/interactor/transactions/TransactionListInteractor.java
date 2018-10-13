package com.bankex.pay.domain.interactor.transactions;

import com.bankex.pay.data.reporitories.transactions.ITransactionListRepository;
import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.models.transaction.ListType;
import com.bankex.pay.domain.models.transaction.ModuleDestinationType;
import com.bankex.pay.domain.models.transaction.TransactionModel;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Gevork Safaryan on 22.06.2018
 */
public class TransactionListInteractor implements ITransactionListInteractor {

    private ITransactionListRepository mTransactionListRepository;

    public TransactionListInteractor(ITransactionListRepository transactionListRepository) {
        mTransactionListRepository = transactionListRepository;
    }

    @Override
    public Single<BaseDomainBean<List<TransactionModel>>> loadTransactions(ListType listtype, ModuleDestinationType moduleDestinationType, String id, int page, int size) {
        return mTransactionListRepository.loadTransactions(listtype, moduleDestinationType, id, page, size);
    }
}
