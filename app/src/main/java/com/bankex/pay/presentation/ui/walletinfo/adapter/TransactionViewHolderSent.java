package com.bankex.pay.presentation.ui.walletinfo.adapter;

import android.view.View;

import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.transaction.TransactionModel;

/**
 * @author Denis Anisimov.
 */
class TransactionViewHolderSent extends TransactionViewHolder {

    public TransactionViewHolderSent(View view) {
        super(view);
    }

    @Override
    public void bind(BaseBankexModel baseBankexModel) {
        TransactionModel transactionModel = (TransactionModel) baseBankexModel;
        baseBind(baseBankexModel);
        String addrto = transactionModel.getAddrto();
        mAddressTextView.setText(addrto);
        String prefix = "- ";
        String valueString = String.valueOf(transactionModel.getValue());
        valueString = prefix + valueString + transactionModel.getToken().getSmbl();
        mBalanceTextView.setText(valueString);
    }
}
