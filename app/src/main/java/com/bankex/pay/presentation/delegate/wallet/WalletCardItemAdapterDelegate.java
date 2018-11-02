package com.bankex.pay.presentation.delegate.wallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bankex.pay.R;
import com.bankex.pay.domain.model.BaseBankexModel;
import com.bankex.pay.domain.model.WalletCardModel;
import com.bankex.pay.presentation.navigation.wallet.IWalletRouter;
import com.bankex.pay.presentation.ui.viewholder.wallet.WalletCardViewHolder;
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;

/**
 * Делегат для вью компонента, отображающего карточку кошелька/токена
 *
 * @author Pavel Apanovskiy on 13/10/2018.
 */
public class WalletCardItemAdapterDelegate extends AbsListItemAdapterDelegate<WalletCardModel, BaseBankexModel, WalletCardViewHolder> {

    private final Context mContext;
    private final IWalletRouter mRouter;
    private final LayoutInflater mInflater;

    public WalletCardItemAdapterDelegate(Context context, IWalletRouter router) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mRouter = router;
    }

    @Override
    protected boolean isForViewType(@NonNull BaseBankexModel item, @NonNull List<BaseBankexModel> items, int position) {
        return item instanceof WalletCardModel;
    }

    @NonNull
    @Override
    protected WalletCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new WalletCardViewHolder(mInflater.inflate(R.layout.wallet_card_item, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull WalletCardModel item, @NonNull WalletCardViewHolder viewHolder, @NonNull List<Object> payloads) {
        viewHolder.getWalletNameTextView().setText(item.getTitle());
        viewHolder.getWalletBalanceTextView().setText(String.valueOf(item.getValue()));
        viewHolder.getWalletLogoImageView().setImageDrawable(mContext.getDrawable(R.drawable.ic_home_black_24dp));

        viewHolder.getWalletLogoImageView().setOnClickListener(getLogoClickListener());
        viewHolder.getWalletRightArrowImageView().setOnClickListener(getRightArrowClickListener());
    }

    private View.OnClickListener getLogoClickListener() {
        return view -> Toast.makeText(mContext, "coming soon", Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener getRightArrowClickListener() {
        return view -> Toast.makeText(mContext, "coming soon", Toast.LENGTH_SHORT).show();
    }
}
