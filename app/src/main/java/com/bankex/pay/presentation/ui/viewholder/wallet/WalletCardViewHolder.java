package com.bankex.pay.presentation.ui.viewholder.wallet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bankex.pay.R;

/**
 * ViewHolder view компонента, отображающего карточку кошелька/токена
 *
 * @author Pavel Apanovskiy on 12/10/2018.
 */
public class WalletCardViewHolder extends RecyclerView.ViewHolder {

    private ImageView mWalletLogoImageView;
    private TextView mWalletNameTextView;
    private TextView mWalletBalanceTextView;
    private ImageView mWalletRightArrowImageView;

    public WalletCardViewHolder(View itemView) {
        super(itemView);
        mWalletLogoImageView = itemView.findViewById(R.id.wallet_logo_image_view);
        mWalletNameTextView = itemView.findViewById(R.id.wallet_name_text_view);
        mWalletBalanceTextView = itemView.findViewById(R.id.wallet_balance_text_view);
        mWalletRightArrowImageView = itemView.findViewById(R.id.wallet_arrow_right_image_view);
    }

    public ImageView getWalletLogoImageView() {
        return mWalletLogoImageView;
    }

    public TextView getWalletNameTextView() {
        return mWalletNameTextView;
    }

    public TextView getWalletBalanceTextView() {
        return mWalletBalanceTextView;
    }

    public ImageView getWalletRightArrowImageView() {
        return mWalletRightArrowImageView;
    }
}
