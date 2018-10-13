package com.bankex.pay.presentation.ui.walletinfo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bankex.pay.R;
import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.transaction.TransactionModel;
import com.bankex.pay.utils.IsoTimeUtils;

/**
 * @author Denis Anisimov.
 */
abstract class TransactionViewHolder extends RecyclerView.ViewHolder {

    protected final TextView mAddressTextView;
    protected final TextView mBalanceTextView;
    protected final TextView mDateNumberTextView;

    public TransactionViewHolder(@NonNull View itemView) {
        super(itemView);
        mAddressTextView = itemView.findViewById(R.id.address_transaction_to_text_view);
        mBalanceTextView = itemView.findViewById(R.id.address_transactions_transaction_balance_text_view);
        mDateNumberTextView = itemView.findViewById(R.id.address_transactions_transaction_date_text_view);
    }

    public abstract void bind(BaseBankexModel baseBankexModel);

    public void baseBind(BaseBankexModel baseBankexModel) {
        TransactionModel transactionModel = (TransactionModel) baseBankexModel;
        String date = IsoTimeUtils.getDifferenceBetweenCurrentAndAnotherDates(mDateNumberTextView.getContext(), transactionModel.getIsotime());
        mDateNumberTextView.setText(date);
    }
}
