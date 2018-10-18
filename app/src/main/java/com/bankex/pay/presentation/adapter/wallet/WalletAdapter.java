package com.bankex.pay.presentation.adapter.wallet;

import android.content.Context;

import com.bankex.pay.domain.BaseBankexModel;
import com.bankex.pay.presentation.delegate.base.BaseTitleItemAdapterDelegate;
import com.bankex.pay.presentation.delegate.wallet.WalletCardItemAdapterDelegate;
import com.bankex.pay.presentation.ui.navigation.wallet.IWalletRouter;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.List;

/**
 * Адаптер для экрана кошелька
 *
 * @author Pavel Apanovskiy on 13/10/2018.
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
