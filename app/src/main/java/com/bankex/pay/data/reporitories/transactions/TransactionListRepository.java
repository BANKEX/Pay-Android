package com.bankex.pay.data.reporitories.transactions;

import com.bankex.pay.data.entity.mappers.TransactionResponseToModelConverter;
import com.bankex.pay.data.entity.mappers.TransactionRowToModelConverter;
import com.bankex.pay.data.network.BankexRestApi;
import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.models.network.BaseResponse;
import com.bankex.pay.domain.models.network.RequestParams;
import com.bankex.pay.domain.models.network.TransactionHeadResponse;
import com.bankex.pay.domain.models.network.TransactionRequest;
import com.bankex.pay.domain.models.network.TransactionResponse;
import com.bankex.pay.domain.models.transaction.ListType;
import com.bankex.pay.domain.models.transaction.ModuleDestinationType;
import com.bankex.pay.domain.models.transaction.TransactionModel;

import java.util.List;

import io.reactivex.Single;

/**
 * Репозиторий транзакций
 *
 * @author Gevork Safaryan on 22.06.2018
 */
public class TransactionListRepository implements ITransactionListRepository {

    private BankexRestApi mBankexRestApi;
    private TransactionResponseToModelConverter mTransactionResponseToModelConverter;

    public TransactionListRepository(BankexRestApi bankexRestApi) {
        mBankexRestApi = bankexRestApi;
        TransactionRowToModelConverter transactionRowToModelConverter = new TransactionRowToModelConverter();
        mTransactionResponseToModelConverter = new TransactionResponseToModelConverter(transactionRowToModelConverter);
    }

    /**
     * Загрузить список транзакций
     *
     * @param listtype              тип списка - эфир или токены
     * @param moduleDestinationType назначение модуля
     * @param id                    идентификатор поиска
     * @param page                  номер страницы которую хотим запросить
     * @param size                  сколько               запрашиваем
     * @return
     */
    @Override
    public Single<BaseDomainBean<List<TransactionModel>>> loadTransactions(ListType listtype, ModuleDestinationType moduleDestinationType, String id, int page, int size) {
        RequestParams params = new RequestParams(id, page, size);
        TransactionRequest transactionRequest = new TransactionRequest(listtype.getListOfTokens(), moduleDestinationType.toString(), params);
        return loadOperationsFromNetwork(transactionRequest).map(mTransactionResponseToModelConverter::convert);
    }

    private Single<BaseResponse<List<TransactionResponse>, TransactionHeadResponse>> loadOperationsFromNetwork(TransactionRequest transactionRequest) {
        return mBankexRestApi.loadTransaction(transactionRequest);
    }
}
