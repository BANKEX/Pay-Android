package com.bankex.pay.presentation.ui.walletinfo.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.transaction.TransactionModel;

/**
 * @author Denis Anisimov.
 */
class TransactionViewHolderRecieved extends TransactionViewHolder {
    public TransactionViewHolderRecieved(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(BaseBankexModel baseBankexModel) {
        TransactionModel transactionModel = (TransactionModel) baseBankexModel;
        baseBind(baseBankexModel);
        String addrfrom = transactionModel.getAddrfrom();
        mAddressTextView.setText(addrfrom);
        String prefix = "+ ";
        String valueString = String.valueOf(transactionModel.getValue());
        valueString = prefix + valueString + transactionModel.getToken().getSmbl();
        mBalanceTextView.setText(valueString);
    }
}
