package com.bankex.pay.data.entity.mappers;

import com.bankex.pay.domain.BaseDomainBean;
import com.bankex.pay.domain.ErrorMessage;
import com.bankex.pay.domain.models.network.BaseResponse;
import com.bankex.pay.domain.models.network.TransactionHeadResponse;
import com.bankex.pay.domain.models.network.TransactionResponse;
import com.bankex.pay.domain.models.transaction.TransactionModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Converter;

/**
 * @author Gevork Safaryan on 22.06.2018
 */
public class TransactionResponseToModelConverter implements Converter<BaseResponse<List<TransactionResponse>, TransactionHeadResponse>, BaseDomainBean<List<TransactionModel>>> {

    private TransactionRowToModelConverter mTransactionRowToModelConverter;

    public TransactionResponseToModelConverter(TransactionRowToModelConverter transactionRowToModelConverter) {
        mTransactionRowToModelConverter = transactionRowToModelConverter;
    }

    @Override
    public BaseDomainBean<List<TransactionModel>> convert(BaseResponse<List<TransactionResponse>, TransactionHeadResponse> value) throws IOException {
        BaseDomainBean<List<TransactionModel>> baseDomainBean = new BaseDomainBean();

        if (value.getRows() != null && !value.getRows().isEmpty()) {
            baseDomainBean.setSuccessObject(mTransactionRowToModelConverter.convert(value.getRows()));
        }
        if (value.getError() != null && value.getError().length() > 0) {
            baseDomainBean.setErrorObject(createErrorMessage(value.getError()));
        }
        return baseDomainBean;
    }

    private ErrorMessage createErrorMessage(String error) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrors(error);
        return errorMessage;
    }
}
