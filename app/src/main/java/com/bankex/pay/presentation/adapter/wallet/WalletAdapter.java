package com.bankex.pay.presentation.adapter.wallet;

import android.content.Context;

import com.bankex.pay.domain.model.BaseBankexModel;
import com.bankex.pay.presentation.delegate.BaseTitleItemAdapterDelegate;
import com.bankex.pay.presentation.delegate.WalletCardItemAdapterDelegate;
import com.bankex.pay.presentation.navigation.wallet.IWalletRouter;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.List;

/**
 * Adapter for wallet screen.
 */
public class WalletAdapter extends ListDelegationAdapter<List<BaseBankexModel>> {
    public WalletAdapter(Context context, IWalletRouter router) {
        delegatesManager.addDelegate(new BaseTitleItemAdapterDelegate(context, router));
        delegatesManager.addDelegate(new WalletCardItemAdapterDelegate(context, router));
    }

    public void setItems(List<BaseBankexModel> list) {
        super.setItems(list);
        notifyDataSetChanged();
    }
}
