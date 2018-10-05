package com.bankex.pay.data.entity.mappers;


import com.bankex.pay.data.entity.mappers.value.ValueConverter;
import com.bankex.pay.domain.models.network.TransactionResponse;
import com.bankex.pay.domain.models.transaction.TransactionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gevork Safaryan on 22.06.2018
 */
public class TransactionRowToModelConverter {

    public List<TransactionModel> convert(List<TransactionResponse> transactionResponses) {
        List<TransactionModel> transactionModels = new ArrayList<>();

        if (transactionResponses != null && !transactionResponses.isEmpty()) {
            for (TransactionResponse transactionResponse : transactionResponses) {
                TransactionModel transactionModel = new TransactionModel();

                transactionModel.setId(transactionResponse.getId());
                transactionModel.setHash(transactionResponse.getHash());
                transactionModel.setBlock(transactionResponse.getBlock());
                transactionModel.setAddrfrom(transactionResponse.getAddrfrom());
                transactionModel.setAddrto(transactionResponse.getAddrto());
                transactionModel.setIsotime(transactionResponse.getIsotime());
                transactionModel.setType(transactionResponse.getType());
                transactionModel.setStatus(transactionResponse.getStatus());
                transactionModel.setError(transactionResponse.getError());
                transactionModel.setIscontract(transactionResponse.getIscontract());
                transactionModel.setIsinner(transactionResponse.getIsinner());
                transactionModel.setValue(new ValueConverter()
                        .convertValueToNormal(transactionResponse.getValue(),
                                transactionResponse.getToken()));
                transactionModel.setToken(transactionResponse.getToken());
                transactionModel.setTxfee(transactionResponse.getTxfee());
                transactionModel.setGasused(transactionResponse.getGasused());
                transactionModel.setGascost(transactionResponse.getGascost());

                transactionModels.add(transactionModel);
            }
        }

        return transactionModels;
    }
}
