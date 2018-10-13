package com.bankex.pay.presentation.ui.walletinfo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.domain.models.BaseBankexModel;
import com.bankex.pay.domain.models.transaction.TransactionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Anisimov.
 */

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionViewHolder> {

    public static final String CALL = "call";
    public static final String TX = "tx";
    private List<BaseBankexModel> mItems = new ArrayList<>();

    public TransactionsAdapter(List<BaseBankexModel> items) {
        mItems = items;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(viewType, parent, false);
        switch (viewType) {
            case R.layout.address_transactions_info_item_recieve:
                return new TransactionViewHolderRecieved(view);
            case R.layout.address_transactions_info_item_sent:
                return new TransactionViewHolderSent(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setData(List<BaseBankexModel> items, boolean clear) {
        if (clear) {
            mItems.clear();
        }
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        String contentType = ((TransactionModel) mItems.get(position)).getType();
        if (contentType.equals(CALL)) {
            return R.layout.address_transactions_info_item_recieve;
        } else if (contentType.equals(TX)) {
            return R.layout.address_transactions_info_item_sent;
        } else return R.layout.address_transactions_info_item_empty;
    }
}
