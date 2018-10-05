package com.bankex.pay.data.reporitories;


import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.models.transaction.ListType;
import com.bankex.pay.domain.models.transaction.ModuleDestinationType;
import com.bankex.pay.domain.models.transaction.TransactionModel;

import java.util.List;

import io.reactivex.Single;

/**
 * @author Gevork Safaryan on 21.06.2018
 */
public interface ITransactionListRepository {

    /**
     * Загрузить список транзакций
     *
     * @param listtype              тип списка - эфир или токены
     * @param moduleDestinationType назначение модуля
     * @param id                    идентификатор поиска
     * @param page                  номер страницы которую хотим запросить
     * @return
     */
    Single<BaseDomainBean<List<TransactionModel>>> loadTransactions(ListType listtype,
                                                                    ModuleDestinationType moduleDestinationType,
                                                                    String id,
                                                                    int page,
                                                                    int size);
}
